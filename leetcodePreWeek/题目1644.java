class Solution{
	public static int translateNum(int num){
        //递归基，num在0到100之间的情况
        if (25 < num && num < 100) { return 1; }
        if (10 <= num && num <= 25) { return 2; }
        if (0 <= num && num < 10){ return 1; }
        //递归条件
        int condition = 0;
        int n = num;//首位或前两位
        int i = 0;//除了多少次10
        while( n >= 10 ){
            if (n <= 25) {
                condition = 1;
                break;
            }
            n = n / 10;
            i++;
        }
        //递归
        switch (condition){
            case 1:
                return translateNum(num - n * (int)Math.pow(10, i))
                        + translateNum(num - (n / 10) * (int)Math.pow(10, i + 1));
            case 0:
                return translateNum(num - n * (int)Math.pow(10, i));
        }
        return 0;
    }
}