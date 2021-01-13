import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.print(dp[N-1][5] + " " + dp[N-1][0]);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		int[] input = new int[3];
		dp = new int[N][6];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++)
			input[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++)
			dp[0][3 + i] = dp[0][i] = input[i];

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++)
				input[j] = Integer.parseInt(st.nextToken());

			int preIndex = i - 1;

			dp[i][0] = Integer.min(dp[preIndex][0], dp[preIndex][1]) + input[0];
			dp[i][1] = Integer.min(dp[preIndex][0], Math.min(dp[preIndex][1], dp[preIndex][2])) + input[1];
			dp[i][2] = Integer.min(dp[preIndex][1], dp[preIndex][2]) + input[2];

			dp[i][3] = Integer.max(dp[preIndex][3], dp[preIndex][4]) + input[0];
			dp[i][4] = Integer.max(dp[preIndex][3], Math.max(dp[preIndex][4], dp[preIndex][5])) + input[1];
			dp[i][5] = Integer.max(dp[preIndex][4], dp[preIndex][5]) + input[2];
		}

		Arrays.sort(dp[N - 1]);
	}
}
