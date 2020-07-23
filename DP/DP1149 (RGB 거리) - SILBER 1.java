import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	
	static int n;
	static String temp;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n][3];
		
		temp = br.readLine();
		StringTokenizer st = new StringTokenizer(temp);
		dp[0][0] = Integer.parseInt(st.nextToken());
		dp[0][1] = Integer.parseInt(st.nextToken());
		dp[0][2] = Integer.parseInt(st.nextToken());
		
		for (int i=1;i<n;i++) {
			temp = br.readLine();
			st = new StringTokenizer(temp);
			dp[i][0] = Integer.parseInt(st.nextToken());
			dp[i][1] = Integer.parseInt(st.nextToken());
			dp[i][2] = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.min(dp[i][0] + dp[i-1][1], dp[i][0] + dp[i-1][2]);
			dp[i][1] = Math.min(dp[i][1] + dp[i-1][0], dp[i][1] + dp[i-1][2]);
			dp[i][2] = Math.min(dp[i][2] + dp[i-1][0], dp[i][2] + dp[i-1][1]);
		}
		
		System.out.println(Math.min(dp[n-1][0], Math.min(dp[n-1][1],dp[n-1][2])));
		
	}
}
