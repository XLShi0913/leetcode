/**
 *用开关控制流程，仅允许字符首尾有空格的题解
 *这题讲实话有点恶心到爷了，条件给的不清楚，真想看看那1480个测试用例都是什么鬼东西
*/
class Solution {
    public boolean isNumber(String s) {
    	int len = s.length();
    	if (len == 0) return false;

    	//首尾空格的处理
    	int lo = 0; int hi = len;
    	while(lo < len && s.charAt(lo) == ' ') lo++;
    	while(hi > 0 && s.charAt(hi - 1) == ' ') hi--;
    	if (hi <= lo) return false;


    	//对非空格串的处理，用开关控制流程，每种情况下仅做判断，并修改控制开关
    	boolean allowSign = true;	//下个字符是否允许为正负号
    	boolean allowE = false;		//下个字符是否允许为E/e
    	boolean allowSpot = true;	//下个字符是否允许为小数点
    	boolean allowEnd = false;	//下个字符是否允许结束
    	//boolean hasSign = false;	//是否已经出现过正负号
    	boolean hasE = false;		//是否已经出现过E/e，对E/e一票否决
    	boolean hasSpot = false;	//是否已经出现过小数点，对小数点一票否决


    	for (int i = lo; i < hi; i++) {
    		if (s.charAt(i) == '+' || s.charAt(i) == '-') {
    			//情况1：当前元素为正负号
    			if (!allowSign) return false;
    			allowSign = false;
    			allowE = false;
    			allowSpot = true;
    			allowEnd = false;
    		}else if (s.charAt(i) == 'E' || s.charAt(i) == 'e') {
    			//情况2：当前元素为E/e
    			if (hasE || !allowE) return false;
    			allowSign = true;
    			allowEnd = false;
    			hasE = true;
    			hasSpot = true;//指数位不许有小数点
    		}else if (s.charAt(i) == '.') {
    			//情况3：当前字符为小数点
    			if (hasSpot || !allowSpot) return false;
    			allowSign = false;
    			allowE = (lo < i && isNum(s.charAt(i - 1)));
    			allowEnd = allowE;
    			hasSpot = true;
    		}else if ( isNum(s.charAt(i)) ) {
    			//情况4：当前字符为数字
    			allowSign = false;
    			allowE = true;
    			allowSpot = true;
    			allowEnd = true;
    		}else{
    			//情况5：当前字符为不合法字符，直接返回
    			return false;
    		}
    	}
    	return allowEnd;
    }

    public boolean isNum(char c) {
    	return ('0' <= c) && (c <= '9');
    }
}