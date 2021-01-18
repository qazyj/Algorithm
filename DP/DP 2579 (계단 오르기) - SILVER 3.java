import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int n;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(dp[n]);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int[] steps = new int[n+1];
		dp = new int[n+1];
		int noJump = 0, jump = 0;
		
		for(int i = 1; i <= n; i++)
			steps[i] = Integer.parseInt(br.readLine());
		
		dp[1] = steps[1];
		if(n>=2)
			dp[2] = steps[1] + steps[2];
		
		for(int i = 3; i <= n; i++) {
			noJump = steps[i] + steps[i-1] + dp[i-3];
			jump = steps[i] + dp[i-2];
					
			dp[i] = Math.max(noJump, jump);
		}
	}
}