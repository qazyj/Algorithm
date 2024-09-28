import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    condition:
    testcase: T
    3 <= k <= 1000000,
    0 <= k[i] <= 10000
 */

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int k = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 0; i < k; i++) pq.add(Long.parseLong(st.nextToken()));

			long answer = 0;
			while (pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				answer += (a + b);
				pq.add(a + b);
			}
			sb.append(answer).append("\n");
		}

		System.out.println(sb);

	}
}