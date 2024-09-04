import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		while(n-- > 0) {
			String[] s = br.readLine().split(" ");
			switch (s[0]) {
				case "push_back":
					stack.addLast(Integer.parseInt(s[1]));
					break;
				case "push_front":
					stack.addFirst(Integer.parseInt(s[1]));
					break;
				case "pop_front":
					if(!stack.isEmpty())
						sb.append(stack.removeFirst()).append("\n");
					else
						sb.append(-1).append("\n");
					break;
				case "pop_back":
					if(!stack.isEmpty())
						sb.append(stack.removeLast()).append("\n");
					else
						sb.append(-1).append("\n");
					break;
				case "size":
					sb.append(stack.size()).append("\n");
					break;
				case "empty":
					if (stack.isEmpty())
						sb.append(1).append("\n");
					else
						sb.append(0).append("\n");
					break;
				case "front":
					if(stack.isEmpty())
						sb.append(-1).append("\n");
					else
						sb.append(stack.peekFirst()).append("\n");
					break;
				case "back":
					if(stack.isEmpty())
						sb.append(-1).append("\n");
					else
						sb.append(stack.peekLast()).append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}
