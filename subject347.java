给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]

/**
 *hash：hash函数（模余）& 冲突处理（线性寻址）
 *完全二叉堆：批量建堆 & 删除下滤
 *快排

 *hash中对0做特殊处理的原因：要用hashtab对应元素是否为0判断这个地方是否有元素，
 *一个位置为0的两种情况：0填在了这个位置 || 没有被使用，
 *为排除前一种情况，直接将hashtab设长一位，且最后一位专门留给0。

 *先交了，然后抽时间把hash、堆和快排都手撕一下，权当复习一下这三块东西
*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int len = nums.length;
        int[] hashtab = new int[len + 1];
        int[] bucket = new int[len + 1];

        for (int num : nums) {
        	int rank = getHash(hashtab, num, len);
        	hashtab[rank] = num;
        	bucket[rank]++;
        }//得到桶数组
        //接下来便有两种处理思路，一是用堆，二是用快排

        floyd(bucket);
        if (k == 1) return Arrays.copyOfRange(hashtab, 0, 1);

        for (int i = 1; i < k; i++) {
        	//下滤，i为当前的堆开始位置
        	swap(bucket, i, len);
        	swap(hashtab, i ,len);

        	int hot = i;
        	while (hot)
        	int maxChild = Math.max()
        }


        
    }

    public int getHash (int[] hashtab, int num, int len) {
    	//基于模余法和线性寻址的hash函数，返回num在hashtab中的秩
    	if (num = 0) {
    		return len;//把末尾位置len直接指定给0
    	}
    	//下面的讨论中num不会为0，而hashtab的最大rank为len - 1
    	int point = num % len;
    	if (num != hashtab[point]) {
    		//出现hash冲突的情况
    		while (hashtab[point] != 0){
    			point++;
    			point %= len;//保证不会碰到hash[len]
    		}
    	}
    	return point;
    }

    public void floyd (int[] bucket) {
    	//floyd批量建堆，基于自底而上的下滤
    	int n = bucket.length;
    	if (n & 1 == 0 && bucket[n >> 2 - 1] < bucket[n - 1]) {
    		swap(bucket, n >> 2 - 1, n - 1);
    		swap(hashtab, n >> 2 - 1, n - 1);
    	}

    	//注意这个循环里边i最小为3
    	for (int i = (n - 1) >> 1 - 1; 0 <= i; i--) {
    		if (bucket[i] < bucket[i << 1 + 1]){
    			swap(bucket, i, i << 1 + 1);
    			swap(hashtab, i, i << 1 + 1);
    		}
    		if (bucket[i] < bucket[(i + 1) << 1]) {
    			swap(bucket, i, (i + 1) << 1);
    			swap(hashtab, i, (i + 1) << 1);
    		}
    	}
    }

	public void swap (int[] bucket, int i1, int i2) {
		int temp = bucket[i1];
		bucket[i1] = bucket[i2];
		bucket[i2] = temp;
	}
}


//********************************************************************************
官方题解——堆
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//********************************************************************************
官方题解——快排
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);
        
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。