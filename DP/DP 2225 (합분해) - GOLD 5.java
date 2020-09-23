import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[201][201];
		
		for(int i=1;i<=K;i++) {
			dp[i][0]=1;
		}
		for(int i=1;i<=K;i++) {
			for(int j=1;j<=N;j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j])%1000000000; // 1000000000으로 나누는 걸 출력할 때 주면 틀렸다고 뜸.
			}
		}
		System.out.println(dp[K][N]);
		
	}

}