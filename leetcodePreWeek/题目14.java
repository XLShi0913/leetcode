class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null){
            return "";
        }
        int conter = 0;
        String res = strs[0];
        for (int i = 0; i < strs.length; i++) {
            res = longestCommonPrefix(res, strs[i]);
            if (res.length == 0) {
                break;
            }
        }
        return new res;
    }
    private String longestCommonPrefix(String chars1, String chars2){
        //子方法
        int len = Math.min(chars1.length(), chars2.length());
        int index = 0;
        while(index < len && chars1.charAt(index) == chars2.charAt(index)){
            index++;
        }
        return chars1.substring(0, index);
    }
}