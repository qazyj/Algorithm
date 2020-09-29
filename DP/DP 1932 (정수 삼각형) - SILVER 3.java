import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); 
        int [][]dp = new int[N+1][N+1];
        int max = 0;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());

                dp[i][j] = Math.max(dp[i][j] + dp[i-1][j], dp[i][j] + dp[i-1][j-1]);
                max = Math.max(dp[i][j], max);
            }
        }
        
        System.out.println(max);
    }
}