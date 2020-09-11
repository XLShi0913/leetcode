class Solution {
    public boolean isMatch(String s, String p) {
    	int m = s.length();
    	int n = p.length();
    	boolean[][] dp = new boolean[m + 1][n + 1];//将空串的情况纳入其中
    	dp[0][0] = true;//两个空串匹配
    	for (int i = 0; i <= m; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (p.charAt(j - 1) == '*') {
    				dp[i][j] = dp[i][j-2];;
    				if (isMatch(s,p,i,j-1)) {
    					dp[i][j] = dp[i][j] || dp[i-1][j];
    				}
    			}
    			else {
    				if (isMatch(s,p,i,j)) {
    					dp[i][j] = dp[i-1][j-1];
    				}
    			}
    		}
    	}
    	return dp[m][n];
    }

    public boolean isMatch(String s, String p, int i, int j) {
    	if (i == 0) return false;//s为空，无法匹配
    	if (p.charAt(j - 1) == '.') return true;
    	return s.charAt(i - 1) == p.charAt(j - 1);
    }
}