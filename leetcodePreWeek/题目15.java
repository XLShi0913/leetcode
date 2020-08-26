//72.21，98.11
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
    	int left;
    	int mid;
    	int right = nums.length - 1;
    	int sum;
    	List<List<Integer>> result = new ArrayList<>();//存放结果
    	while(2 <= right){
    		left = 0;
    		mid = right - 1;
    		while(left < mid){
    			//left和mid不碰头，执行此循环
    			sum = nums[left] + nums[mid] + nums[right];
    			if (sum < 0) {
    				left++;
    			}else if (0 < sum) {
    				mid--;
    			}else {
    				//三数之和等于0的情况
    				ArrayList<Integer> resSingle = new ArrayList<>();
    				resSingle.add(nums[left]);
    				resSingle.add(nums[mid]);
    				resSingle.add(nums[right]);
    				result.add(resSingle);
    				//此处需要过滤重复数据，非输入结果的情况下，重复数据会一视同仁的跳过去
                	while(left < nums.length - 1 && nums[left] == nums[left + 1]){
                		left++;
                	}
                	while(0 < mid && nums[mid] == nums[mid - 1]){
                		mid--;
                	}
                	//更新数据
                	left++;
    			}
    		}
    		//此处也需要过滤重复数据
    		while(0 < right && nums[right] == nums[right - 1]){
    			right--;
    		}
    		right--;
    	}
    	return result;
    }
}