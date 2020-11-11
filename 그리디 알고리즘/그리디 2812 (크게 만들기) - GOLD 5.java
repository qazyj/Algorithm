import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, K;
	static boolean[] check;
	static char[] number;

	public static void main(String[] args) throws Exception {
		SetData();
		FindMaxValue();
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		check = new boolean[N];
		number = br.readLine().toCharArray();
	}

	private static void FindMaxValue() {
		int temp = K;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < number.length; i++) {
			// 스택의 맨 뒤의 값이 number[i]보다 작으면 삭제한다.
			// 아래 조건을 만족할 때까지 반복.
			while (temp > 0 && !stack.isEmpty() && stack.peek() < number[i]) {
				stack.pop();
				temp--;
			}
			stack.push(number[i]);
		}

		StringBuilder sb = new StringBuilder();
		if (stack.size() <= K) {
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
		} else {
			for(int i = 0; i < N-K;i++) {
				sb.append(stack.get(i));
			}
		}

		System.out.println(sb);
	}
}
