import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	
    static int[][] array;
    static boolean[][] check;
    static int N,M;
    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int [] array = new int[N];
        int [] dp = new int[N];
        
        for (int i = 0; i < N; i++) 
            array[i] = Integer.parseInt(br.readLine());
        
        int max = 1;
        for (int i = 0; i < N; i++) {
        	if (dp[i] == 0) 
        		dp[i] = 1;
        	for (int j = 0; j < i; j++) {
        		if (array[i] > array[j]) 
        			dp[i] = Math.max(dp[i], dp[j]+1);
        	}
            if(max<dp[i])
            	max=dp[i];
        }

        System.out.println(N-max);
    }
        
}