import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[][] dp;
	static int[] array;
	
    public static void main(String[] args) throws Exception {
        SetData();
        System.out.println(dp[N-2][array[N-1]]);
    }
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        dp = new long[N][21];
        array = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            array[i]=  Integer.parseInt(st.nextToken());
        }
        dp[0][array[0]]=1;
        
        for(int i = 1; i < N; i++) {
            for(int j = 0; j <= 20; j++) {
                if(dp[i-1][j] != 0) {
                    if(j+array[i] <= 20) {
                        dp[i][j+array[i]] += dp[i-1][j];
                    }
                    if(j-array[i] >= 0) {
                        dp[i][j-array[i]] += dp[i-1][j];
                    }
                }
            }
        }
	}
}