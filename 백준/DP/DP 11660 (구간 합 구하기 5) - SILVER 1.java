import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Algorithm {
	static int[][] array;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		array = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + array[i][j];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(sum(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) + "\n");
		}
		
		System.out.println(sb);
	}

	static int sum(int x1, int y1, int x2, int y2) {
		return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
	}
}
