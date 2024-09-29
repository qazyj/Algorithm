import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    condition:
    testcase: T
    3 <= k <= 500,
    0 <= k[i] <= 10000
 */

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int k = Integer.parseInt(br.readLine());
			int[] arr = new int[k + 1];
			int[][] dp = new int[k + 1][k + 1];
			int[] sum = new int[k + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}

			for (int n = 1; n <= k; n++) {
				for (int from = 1; from + n <= k; from++) {
					int to = from + n;
					dp[from][to] = Integer.MAX_VALUE;
					for (int divide = from; divide < to; divide++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
					}
				}
			}

			sb.append(dp[1][k]).append("\n");
		}

		System.out.println(sb);

	}
}