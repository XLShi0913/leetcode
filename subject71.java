给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combinations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//*******************************************************************************
//解法1，递归，从后往前，因为题目要求顺序，所以出错了
class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> anAns = new ArrayList<Integer>();
        cnk(n, k, anAns);
        return ans;
    }

    
    void cnk(int n, int k, List<Integer> anAns) {
        if (k == 0) {
            ans.add(anAns);
            return;
        }
        if (n == k) {
            for (int i = 1; i <= n; i++) 
                anAns.add(i);
            ans.add(anAns);
            return;
        }

        List<Integer> anotherAns = new ArrayList<>(anAns);
        anAns.add(n);

        cnk(n - 1, k - 1, anAns);
        cnk(n - 1, k, anotherAns);
    }
}
//*****************************************************************
//没办法，小改一下重新调试
class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> anAns = new ArrayList<Integer>();
        cnk(1, n, k, anAns);
        return ans;
    }

    
    void cnk(int lo, int hi, int k, List<Integer> anAns) {
        //初始lo=1, hi=n，为闭区间
        if (k == 0) {
            ans.add(anAns);
            return;
        }
        if (hi - lo + 1 == k) {
            for (int i = lo; i <= hi; i++) 
                anAns.add(i);
            ans.add(anAns);
            return;
        }

        List<Integer> anotherAns = new ArrayList<>(anAns);
        anAns.add(lo);

        cnk(lo + 1, hi, k - 1, anAns);
        cnk(lo + 1, hi, k, anotherAns);
    }
}