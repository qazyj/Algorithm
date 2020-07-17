import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	
	static int N,num;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[11];
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i<11;i++) {
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		
		
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			System.out.println(dp[num]);
		}
		
	}
}
