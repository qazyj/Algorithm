import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] palindrom = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) 			
			palindrom[i] =  Integer.parseInt(st.nextToken());
		
				
		int M = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			result.append(isPalindrome(palindrom,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())) +  "\n");
		}

		System.out.println(result.toString());
	}
	
	static int isPalindrome(int[] palindrom,int a, int b) {
	    while (a++ < b-- && palindrom[a] == palindrom[b]) {	    	
	    }
	    return (a < b) ? 0 : 1;
	}
}