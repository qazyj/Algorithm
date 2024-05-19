import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        boolean[] check = new boolean[cards.length+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < cards.length; i++) {
            if(check[i+1]) continue;

            Stack<Integer> stack = new Stack<>();
            check[i+1] = true;
            stack.push(cards[i]);
            while(!check[stack.peek()]) {
                check[stack.peek()] = true;
                stack.push(cards[stack.peek()-1]);
            }
            pq.add(stack.size());
        }
        if(pq.size() == 1) return 0;
        return pq.poll()*pq.poll();
    }
}