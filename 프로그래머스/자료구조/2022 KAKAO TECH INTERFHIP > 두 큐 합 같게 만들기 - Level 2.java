import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum = 0;
        long queue1Sum = 0;
        long queue2Sum = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i = 0; i < queue1.length; i++) {
            sum += queue1[i];
            queue1Sum += queue1[i];
            q1.add(queue1[i]);
        }
        for(int i = 0; i < queue2.length; i++) {
            sum += queue2[i];
            queue2Sum += queue2[i];
            q2.add(queue2[i]);
        }
        sum/=2;
        for(int i = 0; i < queue1.length*3; i++) {
            if(q1.isEmpty() || q2.isEmpty()) break;

            if(sum < queue1Sum) {
                int value = q1.poll();
                q2.add(value);
                queue1Sum -= value;
                queue2Sum += value;
            } else if(sum < queue2Sum) {
                int value = q2.poll();
                q1.add(value);
                queue2Sum -= value;
                queue1Sum += value;
            } else {
                return answer;
            }
            //System.out.println(queue1Sum + " " + queue2Sum);
            answer++;
        }

        return -1;
    }
}