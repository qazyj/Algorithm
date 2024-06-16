import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        if(isAlright(sb.toString())) answer++;
        for(int i = 0; i < s.length()-1; i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            if(isAlright(sb.toString())) answer++;
        }
        return answer;
    }

    public boolean isAlright(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '[' || c == '{' || c == '(') stack.push(c);
            else {
                if(c == ')') {
                    if(stack.isEmpty() || stack.peek() != '(') return false;
                    stack.pop();
                } else if (c == '}') {
                    if(stack.isEmpty() || stack.peek() != '{') return false;
                    stack.pop();
                } else if(c == ']') {
                    if(stack.isEmpty() || stack.peek() != '[') return false;
                    stack.pop();
                }
            }
        }
        if(stack.size() != 0) return false;
        return true;
    }
}