import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        dp[1] = array[1];

        for(int i = 2; i <= N; i++) {
            dp[i] = array[i];
            for(int j = 0; j <= i / 2; j++)
                dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
        }

        System.out.println(dp[N]);
    }
}
