//直接二分，理论时间复杂度是更高的，因为最大整数是大于数组长度的
class Solution {
    public int findBestValue(int[] arr, int target) {
    	int left = 0;
    	int right = 100000;
    	int mid;
    	int sum;
    	while(left + 1 < right){
    		mid = (left + right) / 2;
    		//...........
    	}
    }
    private int getSum(int[] arr, int upBound){
    	int sum = 0;
    	for (i = 0; i < arr.length; i++) {
    		sum += Math.min(arr[i] , upBound);
    	}
    	return sum;
    }
}