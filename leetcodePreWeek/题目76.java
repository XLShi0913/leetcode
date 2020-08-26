//其实就是斐波那契数列
class Solution {
    public int climbStairs(int n) {
    	Integer a1 = 1;//前一结果
    	Integer a2 = 1;//当前结果
    	int temp;//数据倒运
    	for (int i = 2; i <= n; i++) {
    		temp = a1;
    		a1 = a2;
    		a2 = a1 + temp;
    	}
    	return a2;
    }
}