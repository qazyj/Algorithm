import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
		
    	int cnt = 0;

    	while (true) {
    		n = n % m;
    		System.out.println(n);
    		System.out.println(m/n-1);
    		if (n == 0)
    			break;
    		if (m%n == 0)
    		{
    			cnt = (m / n - 1)*n + cnt;
    			break;
    		}
    		else {
    			cnt = (m / n)*n + cnt;
    			m = m % n;
    		}
    	}
    	
    	System.out.println(cnt);
	}
}
