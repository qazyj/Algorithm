import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 1;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < order.length; i++) {
            stack.push(i+1);

            while(!stack.isEmpty() && stack.peek() == order[answer-1]) {
                stack.pop();
                answer++;
            }
        }

        return answer-1;
    }
}