import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for(int i = 4; i < 101; i++) 
        	dp[i] = dp[i-2] + dp[i-3];
        
        for(int i = 0; i< N; i++)
        	System.out.println(dp[Integer.parseInt(br.readLine())]);
    } 
}
