import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, max;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(N-max);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	int temp = Integer.parseInt(st.nextToken());
            dp[temp] = dp[temp-1]+1;
            max = Math.max(max, dp[temp]);
        }
	}
}