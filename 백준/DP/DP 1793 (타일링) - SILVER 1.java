import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Algorithm {
	static StringBuilder sb;
	static BigInteger[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
        Scanner sc = new Scanner(System.in);
		
		dp = new BigInteger[251];
		sb = new StringBuilder();
		
        dp[0] = new BigInteger("1"); 
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        for(int i=3; i<=250; i++) {
            dp[i] = dp[i-2].multiply(new BigInteger("2"));
            dp[i] = dp[i].add(dp[i-1]);
        }
        
        while(sc.hasNextInt()){
            int n = Integer.parseInt(sc.next());
            sb.append(dp[n] + "\n");
         }
	}
}