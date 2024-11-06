import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while(true) {
			String s = br.readLine();
			if(s.equals(".")) break;

			Stack<Character> stack = new Stack<>();
			boolean check = false;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '(' || s.charAt(i) == '[') stack.push(s.charAt(i));
				else if(s.charAt(i) == ')') {
					if(stack.size() == 0 || stack.peek() != '(') {
						check = true;
						break;
					} else {
						stack.pop();
					}
				} else if(s.charAt(i) == ']') {
					if(stack.size() == 0 || stack.peek() != '[') {
						check = true;
						break;
					} else {
						stack.pop();
					}
				}
			}
			if(check || stack.size() > 0) sb.append("no").append("\n");
			else sb.append("yes").append("\n");
		}
		System.out.println(sb);
	}
}