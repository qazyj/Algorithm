import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times);

        long start, mid, end;
        start = 0;
        end = Long.MAX_VALUE;
        long sum;

        while(start <= end){

            mid = (start + end) / 2;

            sum = 0;
            for(int i=0; i<times.length; i++){
                sum += mid / times[i];

                if(sum >= n)
                    break;
            }

            if(n > sum){
                start = mid + 1;
            }
            else{
                end = mid - 1;
                answer = Math.min(answer, mid);
            }
        }

        return answer;
    }
}