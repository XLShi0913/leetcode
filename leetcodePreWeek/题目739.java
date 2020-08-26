class Solution{
	public int[] dailyTemperatures(int[] T) {
		int lengthOfT = T.length;
		for (i = 0; i < lengthOfT; i++) {
			j = i;
			while(true){
				j++;
				if (T[i] < T[j]) {
					T[i] = j - i;
					break;
				}else if (j >= lengthOfT - 1) {
					T[i] = 0;
					break;
				}
			}
		}
		return T;
    }
}