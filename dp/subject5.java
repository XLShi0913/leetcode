class Solution {
    public String longestPalindrome(String s) {
    	int len = s.length();
    	boolean[] statusJi = new boolean[len];
    	boolean[] statusOu = new boolean[len];
    	boolean[] cache = new boolean[len];

    	for (boolean statu : statusJi) {statu = true;}
    	int lo = 0;
    	int hi = 0;
    	
    	int diff = 1;//表示当前字串长度-1
    	for (int i = diff; i < len; i ++) {
    		if (s.charAt(i) == s.charAt(i - 1)) {
    			statusOu[i] = true;
    			hi = i; lo = hi - diff;
    		}
    	}//初始化完成，后段是有用段

    	while(diff < len - 1) {//遍历不同长度的子串
    		diff++;
    		if((diff & 1) == 0) {//长度为奇数，diff为偶数
    			for (int i = diff; i < len; i++) {
    				if (statusJi[i - 1] && s.charAt(i - diff) == s.charAt(i)){
    					cache[i] = true;
    					hi = i; lo = hi - diff;
    				}else{
    					cache[i] = false;
    				}
    				//交换引用，这个地方处理得好可以不用缓存数组
    				boolean[] temp = statusJi;
    				statusJi = cache;
    				cache = temp;
    			}
    		}else {//长度为偶数
    			for (int i = diff; i < len; i++) {
    				if (statusOu[i - 1] && s.charAt(i - diff) == s.charAt(i)){
    					cache[i] = true;
    					hi = i; lo = hi - diff;
    				}else{
    					cache[i] = false;
    				}
    				boolean[] temp = statusOu;
    				statusOu = cache;
    				cache = temp;
    			}
    		}
    	}
    	return s.substring(lo, hi + 1);
    }
}
//**************************************************************************************
//新设计，不用缓存数组
class Solution {
    public String longestPalindrome(String s) {
    	int len = s.length();
    	boolean[] statusJi = new boolean[len];
    	boolean[] statusOu = new boolean[len - 1];
    	int lo = 0;
    	int hi = 0;
    	int diff = 1;//当前lo - hi的值
    	for (boolean st : statusJi) st = true;
    	for (int i = 0; i < len - 1; i++) {
    		if (s.charAt(i) == s.charAt(i + 1)) {
    			statusOu[i] = true;
    			lo = i; hi = lo + 1;
    		}
    	}//初始化完成
	
    	while (diff < len - 1) {
    		diff++;
    		if ((diff & 1) == 0) {
    			for (int i = (diff>>1); i < len-(diff>>1); i++) {
    				if (statusJi[i] && s.charAt(i-(diff>>1)) == s.charAt(i+(diff>>1))) {
    					statusJi[i] = true;
    					lo = i-(diff>>1); hi = i+(diff>>1);
    				}
    			}
    		}else {
    			for (int i = (diff>>1); i < len - (diff>>1) - 1; i++) {
    				if (statusOu[i] && s.charAt(i-(diff>>1)) == s.charAt(i+(diff>>1)+1)) {
    					statusOu[i] = true;
    					lo = i-(diff>>1); hi = i+(diff>>1)+1; 
    				}
    			}
    		}
    	}
    	return s.substring(lo, hi + 1);
    }
}