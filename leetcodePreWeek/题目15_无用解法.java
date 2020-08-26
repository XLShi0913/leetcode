import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            twoSum(nums, i, result);//逐次更新
            //跳过重复数据
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
            	i++;
            }
        }
        return result;
    }
    //子方法，此方法用于解决两数问题
    private static void twoSum(int[] nums, int index, List<List<Integer>> result){
        //输入的数组需要是有序数组
        int left = 0;
        int right = nums.length - 1;
        int sum;
        //需要找出所有的结果
        while(true){
        	//判断之前排除掉第三数
        	if (left == index) {
        		left ++;
        	}else if (right == index) {
        		right ++;
        	}
        	//判断是否需要跳出循环
        	if (right <= left) {
        		break;
        	}
            sum = nums[left] + nums[right];
            //实质内容
            if (sum + nums[index] == 0) {
                ArrayList<Integer> resSingle = new ArrayList<>();
                resSingle.add(nums[left]);
                //按大小顺序插入
                if (nums[right] < nums[index]) {
                	resSingle.add(nums[right]);
                	resSingle.add(nums[index]);
                }else{
                	resSingle.add(nums[index]);
                	resSingle.add(nums[right]);
                }
                result.add(resSingle);
                //此处需要过滤重复数据
                //while(nums[left] == nums[left + 1]){left++;}
                //while(nums[right] == nums[right - 1]){right--;}
                //更新数据
                left++;
            }else if (sum + nums[index] < 0) {
                left++;
            }else{
                right--;
            }
        }
    }
}