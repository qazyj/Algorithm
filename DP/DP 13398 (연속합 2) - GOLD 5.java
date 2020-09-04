import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        int [] dp = new int[n];
        int [] dp2 = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) 
        	array[i] = Integer.parseInt(st.nextToken());
        
       
        dp[0] = array[0];
        dp2[0] = array[0];
	    int max = array[0];
	    for(int i=1; i<n; i++){
	    	 dp[i] = Math.max(dp[i-1] + array[i], array[i]);
	    	 dp2[i] = Math.max(dp2[i-1] + array[i], dp[i-1]);
	         
	         max = Math.max(max, Math.max(dp[i],dp2[i]));
	    }

	    System.out.println(max);
    }
}