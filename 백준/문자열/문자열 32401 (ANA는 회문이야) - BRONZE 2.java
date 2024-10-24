import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(arr[i] != 'A' && arr[i] != 'N') continue;

			if(arr[i] == 'A') {
				if(stack.size() > 0 && stack.peek() == 'A') {
					while(!stack.isEmpty()) stack.pop();
				} else {
					if(stack.size() == 2) {
						answer++;
						while(!stack.isEmpty()) stack.pop();
					}
				}
				stack.push('A');
			} else {
				if(stack.size() > 0 && stack.peek() == 'N') {
					while(!stack.isEmpty()) stack.pop();
				} else if(stack.size() != 0){
					stack.push('N');
				}
			}
		}
		System.out.println(answer);
	}
}