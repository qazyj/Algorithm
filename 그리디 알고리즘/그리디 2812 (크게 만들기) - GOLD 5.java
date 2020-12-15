import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static char[] number;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		FindMaxValue();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		number = br.readLine().toCharArray();
		sb = new StringBuilder();
	}

	private static void FindMaxValue() {
       		Deque<Character> deque = new ArrayDeque<>();
		for (int i = 0; i < number.length; i++) {
			// 스택의 맨 뒤의 값이 number[i]보다 작으면 삭제한다.
			// 아래 조건을 만족할 때까지 반복.
			while (K > 0 && !deque.isEmpty() && deque.getLast() < number[i]) {
        				deque.removeLast();
				K--;
			}
			deque.addLast(number[i]);
		}

	    while(deque.size() > K) {
	       	sb.append(deque.removeFirst());
	    }
	}
}