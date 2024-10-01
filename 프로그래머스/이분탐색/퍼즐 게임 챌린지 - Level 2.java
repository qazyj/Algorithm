class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = diffs[0];
        int right = 0;
        int n = diffs.length;
        for(int i = 0; i < n; i++) {
            right = Math.max(right, diffs[i]);
        }

        while(left <= right) {
            int mid = (left+right)/2;

            long sum = times[0];
            for(int i = 1; i < n; i++) {
                if(diffs[i] <= mid) {
                    sum += times[i];
                } else {
                    sum += (times[i-1] + times[i])*(diffs[i]-mid);
                    sum += times[i];
                }
            }

            if(sum <= limit) {
                right = mid-1;
            } else {
                left = mid+1;
            }

        }

        return left;
    }
}