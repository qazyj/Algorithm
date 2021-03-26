import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int [] dp = new int[N];
		int [] a = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = 1;
        for (int i = 0; i < N; i++) {
        	if (dp[i] == 0) 
        		dp[i] = 1;
        	for (int j = 0; j < i; j++) {
        		if (a[i] > a[j]) 
        			dp[i] = Math.max(dp[i], dp[j]+1);
        	}
            if(max<dp[i])
            	max=dp[i];
        }

        System.out.println(max);
	}
}