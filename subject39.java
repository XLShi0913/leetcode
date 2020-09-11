给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> anAns = new ArrayList<Integer>();
        Arrays.sort(candidates);
        addCombine(target, candidates, 0, candidates.length, anAns);
        return ans;
    }

    void addCombine(int target, int[] candidates, int lo, int hi, List<Integer> anAns) {
        if (target == 0) {
            ans.add(anAns);
            return;
        }
        if (lo == hi || target < candidates[lo]) return;

        List<Integer> anotherAns = new ArrayList<Integer>(anAns);
        anotherAns.add(candidates[lo]);
        
        addCombine(target - candidates[lo], candidates, lo, hi, anotherAns);
        addCombine(target, candidates, lo + 1, hi, anAns);
    }
}