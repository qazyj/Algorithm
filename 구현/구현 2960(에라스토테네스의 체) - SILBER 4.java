import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] check = new boolean[N+1];

	    int count = 0;

	    Arrays.fill(check, Boolean.FALSE);

	    for(int i = 2; i <= N; i++) {
	        for(int j = i; j <= N; j += i) {
	            if(check[j])
	                continue;
	            check[j] = true;
	            count ++;
	            if(count == K) {
	                System.out.println(j);
	                System.exit(0);
	            }
	        }
	    }
	  }
}