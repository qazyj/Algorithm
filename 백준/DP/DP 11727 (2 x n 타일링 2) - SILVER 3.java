import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(dp[N]);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		
		dp[1] = 1;
		if(N == 1) return;
		dp[2] = 3;
		if(N == 2) return;
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]*2) % 10007;
		}
	}
}
