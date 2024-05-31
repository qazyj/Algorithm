import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Node> stack = new Stack<>();
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && stack.peek().value > prices[i]) {
                Node n = stack.pop();
                answer[n.index] = i-n.index;
            }
            stack.push(new Node(i, prices[i]));
        }

        while(!stack.isEmpty()) {
            Node n = stack.pop();
            answer[n.index] = prices.length-1-n.index;
        }
        return answer;
    }
}

class Node {
    int index;
    int value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}