给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


class Solution {
    public int integerBreak(int n) {
    	if (n <= 3) return n - 1;
    	int[] dp = new int[n];
    	for (int i = 0; i < 3; i++) dp[i] = i + 1;

    	for (int i = 3; i < n; i++) {
    		for (int j = 0; j < i; j++) {
    			dp[i] = Math.max(dp[i], dp[j] * (i - j));
    		}
    	}

    	return dp[n - 1];
    }
}

//**********************************************************************************
//答案解法，仅需要考虑相差为2和相差为3的两个数即可，甚至还可优化一波空间
class Solution {
    public int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。