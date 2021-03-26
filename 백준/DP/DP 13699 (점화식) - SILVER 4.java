import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int N;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(SaveValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
	
	private static long SaveValue() {
		long dp[] = new long[N + 1];
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - 1 - j];
			}
		}
		
		return dp[N];
	}
}
