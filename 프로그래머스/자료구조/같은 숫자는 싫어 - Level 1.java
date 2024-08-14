import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for(int a : arr) {
            if(!stack.isEmpty() && stack.peek() == a) continue;

            stack.push(a);
        }
        int[] answer = new int[stack.size()];
        int index = stack.size()-1;
        while(!stack.isEmpty()) {
            answer[index--] = stack.pop();
        }

        return answer;
    }
}