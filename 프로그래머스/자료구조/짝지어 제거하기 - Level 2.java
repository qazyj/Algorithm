import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        stack.push(arr[0]);
        for(int i = 1; i < arr.length; i++) {
            if(!stack.isEmpty() && stack.peek() == arr[i]) stack.pop();
            else stack.push(arr[i]);
        }

        if(stack.isEmpty()) return 1;
        return 0;
    }
}