class Solution {
    public boolean PredictTheWinner(int[] nums) {
    	return Predicate(nums, 0, nums.length - 1, 0);
    }

    //用于递归的子方法
    public boolean Predicate(int[] nums, int lo, int hi, int diff) {
    	//diff: 玩家一手里的数比玩家二大多少
    	//递归基，平凡情况
    	if (hi <= lo + 1) 
    		return (hi == lo) ? (0 <= diff) : (0 <= diff + nums[lo]);
    	//递归部分，玩家一取一个，玩家b再取一个，分三种情况
    	int diff1 = (nums[lo] < nums[hi]) ? 
    		(diff + nums[hi] - nums[lo]) : (diff - nums[hi] + nums[lo]);
    	int diff2 = diff + nums[lo] - nums[lo + 1];
    	int diff3 = diff + nums[hi] - nums[hi - 1];
    	//三种情况只要有一种是可以赢的就行了
    	return Predicate(nums, lo + 1, hi - 1, diff1) ||
    		Predicate(nums, lo + 2, hi, diff2) || Predicate(nums, lo, hi -  2, diff3);
    }
}//错误解法，没考虑玩家二会做出对自己最有利的选择
//*************************************************************************************
//尝试使用动态规划，递推式+结果存储
/**
 *发现动态规划掌握薄弱，进行专项练习
 *本题中，逻辑上使用二维数组，dp[i][j]表示剩余元素范围为[i,j]时，当前玩家与另一玩家的分数之差
 *dp[i][j]状态仅可能从上一玩家手中接手，并在其前端或后端
*/
class Solution {
    public boolean PredictTheWinner(int[] nums) {
    	int len = nums.length;
        int[] status = new int[len];
        for (int i = 0; i < len; i++) 
        	status[i] = nums[i];
        //int i1 = 0; int i2 = nums.length - 1;//status有用范围，左闭右开
        int diff = 0;//j-i
        for(int i2 = nums.length - 1; 0 < i2; i2--) {
        	for (int i1 = 0; i1 < i2; i1++) {
        		status[i1] = Math.max(nums[i1] - status[i1 + 1], 
        			nums[i1 + diff] - status[i1]);
        	}
        	diff++;
        }
        return status[0] >= 0;
    }
}