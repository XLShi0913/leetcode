/**
 *有三个条件：不同行、不同列、不同斜
 *在一个结果的每个String中有且仅有一个Q，其余都是'.'
 *用一个int[n]数组position记录每个String中Q的位置，则保证了不同行
 *在posiiton中仅允许存在0~n-1，且每个数字允许用一次，则保证了不同列
 *同斜：index之差与数字之差相等
 *本解法就列出来了一个解
 *本解法实际上是一个DFS
*/
class Solution {

	List<List<String>> ans = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
    	int[] position = new int[n];
		int indexOfPos = 0;
    	boolean[] status = new boolean[n];
    	//状态为false表示该位置未被占用，该数字可用

    }

    public List<String> bulidList(int[] position) {
    	int len = position.length;
    	char[] chars = new char[len];
    	List<String> oneAns = new ArrayList<String>();
    	for (char aChar : chars)
    		aChar = '.';
    	for (int i = 0; i < len; i++) {
    		chars[position[i]] = 'Q';
    		oneAns.add(new String(chars));
    		chars[position[i]] = '.';
    	}
    	return oneAns;
    }

    public boolean legitimacy(int[] position, int indexOfPos, boolean[] status) {
    	//判断是否同时满足不同列、不同斜条件，true表示合法
    	if (indexOfPos == 0) {
    		return true;
    	}
		if (status[position[indexOfPos]] == true) return false;//先排除不满足列条件的情况
    	for (int i = 0; i < indexOfPos; i++) {
    		if ( Math.abs(position[indexOfPos] - position[i]) == (indexOfPos - i))
    			return false;
    	}
    	return return true;
    }

    public void nQueens(int[] position,	//记录当前结果的数组
    	int indexOfPos, 				//结果数组的指针
    	boolean[] status, 				//记录列状态
    	int preNum) 					//用于回溯时初值的选取
    {
    	//说明找到了一个解
    	if (indexOfPos == position.length) {
    		ans.add(bulidList(position));
    	}
    	//为indexOfPos取一个数，依据数的合法性做出不同的分支
    	position[indexOfPos] = preNum + 1;
    	if (legitimacy(position, indexOfPos, status)) {
    		//当前数字合法的情况
    		status[position[indexOfPos]] = true;//在状态栏声明占用
    		indexOfPos++;
    		nQueens(position, indexOfPos, status, -1);
    	}else {
    		//当前数字不合法的情况，分两种处理：平移or回溯
    		while (position[indexOfPos] < position.length - 1) {
    			//当前取的数后边还有数，则平移，直至寻找到合法数字下行，或index越界回溯
    			position[indexOfPos] = position[indexOfPos] + 1;
    			if (legitimacy(position, indexOfPos, status)) {
    				status[position[indexOfPos]] = true;//在状态栏声明占用
    				indexOfPos++;
    				nQueens(position, indexOfPos, status, -1);
    			}
    		}
    		if (indexOfPos == 0) {
    			return;//首元素都没有合适位置，则退出
    		}
    		//回溯，注意每个数都取尽可能小的数，故而回溯考察的数都应比原数大
    		//position[indexOfPos] = 0;//没必要
    		indexOfPos--;
    		status[position[indexOfPos]] = false;//在状态栏解除封印
    		nQueens(position, indexOfPos, status, position[indexOfPos]);
    	}
    }
}

//**********************************************************************************
/**
 *正确解法 （参考了答案）
 *用集合
*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                //下面这段是回溯
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}

/*作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/n-queens/solution/nhuang-hou-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/