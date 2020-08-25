class Solution {

	List<Integer> temp = new ArrayList<Integer>();				//当前子序列
	List<List<Integer>> ans = new ArrayList<List<Integer>>();	//最终结果
	Set<Integer> set = new HashSet<Integer>();					//因为set有自动判重方法
	int n;	//给定数组长度的二进制长度

    public List<List<Integer>> findSubsequences(int[] nums) {
    	n = nums.length;
    	//对从1到2^n-1的每个数生成子序列
    	for (int i = 0; i < (1 << n); i++) {
    		gettemp(i, nums);
    		int hashValue = getHash(263, (int)1E9 + 7);
    		if (check() && !set.contains(hashValue)) {
    			ans.add(new ArrayList<Integer>(temp));
    			set.add(hashValue);
    		}
    	}
    	return ans;
    }

    //按整数索引获取一个子序列
    public void gettemp(int mask, int[] nums){
    	temp.clear();
    	for (int i = 0; i < n; i++){
    		if ((mask & 1) != 0) 
    			temp.add(nums[i]);
    		mask >>= 1;
    	}
    }

    //求数组temp的hash码，多项式法，也叫Rabin-Karp编码
    public int getHash(int base, int mod){
    	int hashValue = 0;
    	for (int x : temp) {
    		hashValue = hashValue * base % mod + x + 101;
    		hashValue %= mod;
    	}
    	return hashValue;
    }

    //检查当前子序列是否为递增子序列的方法
    public boolean check(){
    	for (int i = 1; i < temp.size(); i++) {
    		if (temp.get(i) < temp.get(i - 1)) {
    			return false;
    		}
    	}
    	return temp.size() >= 2;//通过审核之后只需长度大于1就返回true
    }
}