import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		FindMaxValue();
		System.out.println(dp[N]);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new long[101];
	}

	public static void FindMaxValue() {
		for(int i = 1; i <= N; i++) {
			dp[i]=dp[i-1]+1;
			for(int j = 1; j <= i - 3; j++) {
				dp[i] = Math.max(dp[i], (j+1)*dp[i-(j+2)]);
			}
		}
	}

}