class Solution {
    List<String> ans = new ArrayList<String>();
    char[] table = {'a', 'a'+3, 'a'+6, 'a'+9, 'a'+12, 'a'+15, 'a'+19, 'a'+22, 'a'+26};

    public List<String> letterCombinations(String digits) {
    	if (digits.equals("")){
            return ans;
        }
        char[] digitsArray = digits.toCharArray();
    	int len = digitsArray.length;
    	int[] nums = new int[len];
    	for (int i = 0; i < len; i++) 
    		nums[i] = (int) (digitsArray[i] - '0');
    	char[] temp = new char[len];
    	addToTemp(temp, nums, 0, len);
    	return ans;
    }

    //主方法，将一个int数组加到StringBuilder上，此处hi是不变的
    public void addToTemp(char[] temp, int[] nums, int lo, int hi){
    	//递归基，两个指针中间没元素了，可以将temp加到ans序列中
    	if (hi <= lo) {
    		ans.add(new String(temp, 0, hi));
    		return;
    	}
    	//开始递归，尾递归好像可以写成迭代
    	for (int i = table[nums[lo] - 2]; i < table[nums[lo] - 1]; i++) {
    		temp[lo] = (char) i;
    		addToTemp(temp, nums, lo + 1, hi);
    	}
    	lo++;
    }
}

/*
遇到的解答错误：输入""时的错误，分析如下：
输入字符串为""时，转换成的字符数组temp是长度为0的数组，转到主方法中直接执行递归基部分
在递归基部分中，将temp转换成字符串""add到结果ans中，输出的结果为[""]
然而这是不严谨的，因为应该输出一个空字符串列表，而非有一个空字符串元素的列表，两者是不同的
分析原因，在主方法中，判定hi与lo碰头当且仅当所有数字都并到了temp数组，而这种特殊情况仅当输入为""时方才出现
所以直接在方法前面加一句判断即可
*/