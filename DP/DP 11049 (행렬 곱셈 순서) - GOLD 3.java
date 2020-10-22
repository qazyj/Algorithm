import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {	
    static int[][] dp;
    static int[][] array;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        array = new int[N][2];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j)
                    dp[i][j]=0;
                else
                    dp[i][j]=Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<N;i++){
            for(int j=0;j+i<N;j++){
                setDP(j,j+i);
            }
        }
        System.out.println(dp[0][N-1]);
    }

    private static void setDP(int start, int end) {
        for(int i=start;i<end;i++){
            int sum = dp[start][i] + dp[i+1][end] + array[start][0]*array[i][1]*array[end][1];
            dp[start][end] = Math.min(sum,dp[start][end]);
        }
    }
}
