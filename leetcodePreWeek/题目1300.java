class Solution {
    public int findBestValue(int[] arr, int target) {
    	Arrays.sort(arr);
    	int len = arr.length;
    	int sumNew = 0;//存放改变后数组的和
    	int sumOld = 0;//存放原数组的和
    	int i = 0;//循环计数器
    	while(i < len && sumNew < target){
    		sumNew = sumOld + (len - i) * arr[i];
    		sumOld += arr[i];
    		i++;//下回合要考察的元素序号
    	}
    	i--;//这才是当前元素序号
        if (i == len - 1 || sumNew == target) {
    		//原元素和小于value的情况
    		return arr[i];
    	} else if(i == 0){
            return ( (target/len + 1) * len - target < target - target / len * len ) ?
                (target/len + 1) : target / len;
        }

    	//一般情况，用二分查找（sumNew大于target的情况）
    	sumOld -= arr[i];
    	int left = arr[i - 1];
    	int right = arr[i];
    	int mid;

    	while( left + 1 < right ){
    		mid = (left + right) / 2;
    		sumNew = sumOld + (len - i) * mid;
    		if (sumNew == target) {
    			return mid;
    		}else if (sumNew < target) {
    			left = mid;
    		}else{
    			right = mid;
    		}
    	}
    	//此时left + 1 = right，且中间元素都被考察完毕，需要返回两者其中之一
    	return ( sumOld + (len - i) * right - target < target - sumOld - (len - i) * left ) ?
    		right : left;
    }
}