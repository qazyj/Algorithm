import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        return binerySearch(distance, rocks, n);
    }

    private static int binerySearch(int distance, int[] rocks, int n){
        long answer = 0;
        long left = 1, right = distance, mid = 0;

        while(left <= right){
            int count = 0;
            int prev = 0;
            mid = (left + right) / 2;

            for(int i = 0 ; i < rocks.length ; ++i){
                if(rocks[i] - prev < mid){
                    count++;
                } else {
                    prev = rocks[i];
                }
            }

            if(distance - prev < mid) count++;

            if(count <= n){
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return (int)answer;
    }
}