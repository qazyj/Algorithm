import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        PriorityQueue<Integer> pA = new PriorityQueue<>();
        PriorityQueue<Integer> pB = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < A.length; i++) {
            pA.add(A[i]);
            pB.add(B[i]);
        }

        while(!pA.isEmpty()) {
            answer += (pA.poll()*pB.poll());
        }

        return answer;
    }
}