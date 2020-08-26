class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;//负数不可能是回文数
        }
        int conter = 0;
        int xSingle = x;//用于判断数字位数
        int xFa;//用于数字倒运
        while(xSingle != 0){
            xSingle /= 10;
            conter++;
        }//判断x有几位
        int[] arrayOfXOld = new int[conter];//反转前
        int[] arrayOfXNew = new int[conter];//反转后
        for(int i = 0; i < conter; i++){
            xFa = x;
            x /= 10;
            arrayOfXOld[conter - i - 1] = xFa - x * 10;
            arrayOfXNew[i] = xFa - x * 10;
        }
        for(int i = 0; i < conter; i++){
            if(arrayOfXNew[i] != arrayOfXOld[i]){
                return false;
            }
        }
        return true;
    }
}