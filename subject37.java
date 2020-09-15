编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。


Note:

给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sudoku-solver
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//*******************************************************************************
// 第一次提交通过的代码
class Solution {

    int[] hangInfo = new int[9];
    int[] lieInfo = new int[9];
    int[] gongInfo = new int[9];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = (int)board[i][j] - (int)('0');
                    changeInfo(i, j, num);
                }
            }
        }//初始化信息完毕
        solve(board);
    }

    void changeInfo (int hang, int lie, int num) {
        //根据位置上的数字改变行、列、宫状态
        hangInfo[hang] += 1 << (num - 1);
        lieInfo[lie] += 1 << (num - 1);
        gongInfo[lie/3*3+hang/3] += 1 << (num - 1);
    }

    void cleanInfo (int hang, int lie, int num) {
        //清除设定的行、列、宫状态
        hangInfo[hang] -= 1 << (num - 1);
        lieInfo[lie] -= 1 << (num - 1);
        gongInfo[lie/3*3+hang/3] -= 1 << (num - 1);
    }

    boolean check (int hang, int lie, int num) {
        //若当前数字在该位置合法，则返回true
        return ((hangInfo[hang] >> (num - 1)) & 1) == 0 
            && ((lieInfo[lie] >> (num - 1)) & 1) == 0
            && ((gongInfo[lie/3*3+hang/3]) >> (num - 1) & 1) == 0;
    }

    boolean solve (char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    List<Integer> list = new ArrayList<>();
                    for (int num = 1; num <= 9; num++) {
                        if(check(i, j, num)) list.add(num);
                    }//记录可能的解集
                    while (!list.isEmpty()) {
                        int num = list.remove(list.size() - 1);
                        changeInfo(i, j, num);
                        board[i][j] = (char)(num + '0');

                        boolean flag = solve(board);
                        if (flag) return true;
                        board[i][j] = '.';
                        cleanInfo(i, j, num);
                    }
                    return false;
                }
            }
        }
        return true;
    }
}

//*******************************************************************************
// 轻微改进
class Solution {

    int[] hangInfo = new int[9];
    int[] lieInfo = new int[9];
    int[] gongInfo = new int[9];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = (int)board[i][j] - (int)('0');
                    changeInfo(i, j, num);
                }
            }
        }//初始化信息完毕
        solve(board, 0);
    }

    void changeInfo (int hang, int lie, int num) {
        //根据位置上的数字改变行、列、宫状态
        hangInfo[hang] += 1 << (num - 1);
        lieInfo[lie] += 1 << (num - 1);
        gongInfo[lie/3*3+hang/3] += 1 << (num - 1);
    }

    void cleanInfo (int hang, int lie, int num) {
        //清除设定的行、列、宫状态
        hangInfo[hang] -= 1 << (num - 1);
        lieInfo[lie] -= 1 << (num - 1);
        gongInfo[lie/3*3+hang/3] -= 1 << (num - 1);
    }

    boolean check (int hang, int lie, int num) {
        //若当前数字在该位置合法，则返回true
        return ((hangInfo[hang] >> (num - 1)) & 1) == 0 
            && ((lieInfo[lie] >> (num - 1)) & 1) == 0
            && ((gongInfo[lie/3*3+hang/3]) >> (num - 1) & 1) == 0;
    }

    boolean solve (char[][] board, int hang) {
        for (int i = hang; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    List<Integer> list = new ArrayList<>();
                    for (int num = 1; num <= 9; num++) {
                        if(check(i, j, num)) list.add(num);
                    }//记录可能的解集
                    while (!list.isEmpty()) {
                        int num = list.remove(list.size() - 1);
                        changeInfo(i, j, num);
                        board[i][j] = (char)(num + '0');
                        boolean flag = solve(board, i);
                        if (flag) return true;
                        cleanInfo(i, j, num);
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return true;
    }
}

//********************************************************************************
// 参考答案，位运算技巧
class Solution {
    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs(board, pos + 1);
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。