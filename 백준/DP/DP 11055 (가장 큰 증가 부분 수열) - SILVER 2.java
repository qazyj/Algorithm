import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[] array, dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(DP());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		dp = new int[N];
		array = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
		
	}
	
	private static int DP() {
        int max = 1;
        for (int i = 0; i < N; i++) {
        	if (dp[i] == 0) 
        		dp[i] = array[i];
        	for (int j = 0; j < i; j++) {
        		if (array[i] > array[j]) 
        			dp[i] = Math.max(dp[i], dp[j]+array[i]);
        	}
            if(max<dp[i])
            	max=dp[i];
        }

        return max;
	}
}
