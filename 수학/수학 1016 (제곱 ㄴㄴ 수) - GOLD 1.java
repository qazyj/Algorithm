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
		
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		int range = (int) (max-min+1);
		
		boolean [] check = new boolean[range];
		//Arrays.fill(check,Boolean.FALSE);
		
		for(int i=2;i*i<=max;i++) {
			long index = i*i;
			long startNumber = min +(index - (min%index))%index;
			
			for(long j = startNumber; j<=max;j+=index) {
				check[(int) (j-min)] = true;
			}
		}
		
        int count = 0;
        for(int i = 0; i < range; i++){
            if(!check[i])
                count++;
        }
        System.out.println(count);
				
    }
}