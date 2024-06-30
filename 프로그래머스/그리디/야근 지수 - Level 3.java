import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) {
            pq.add(work);
        }

        for(int i = 0; i < n; i++) {
            if(pq.isEmpty()) break;
            int temp = pq.poll();
            if(temp == 1) continue;
            pq.add(temp-1);
        }

        while(!pq.isEmpty()) {
            int temp = pq.poll();
            answer += (temp*temp);
        }
        return answer;
    }
}