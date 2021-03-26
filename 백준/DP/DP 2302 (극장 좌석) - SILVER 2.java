import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int T, answer;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		answer = 1;
		dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= T; i++)
			dp[i] = dp[i - 1] + dp[i - 2];

		int n = Integer.parseInt(br.readLine());
		int start = 0;
		int stop = 0;
		for (int i = 0; i < n; i++) {
			stop = Integer.parseInt(br.readLine());
			answer *= dp[stop - start - 1];
			start = stop;
		}
		
		answer *= dp[T-stop];
	}
}
