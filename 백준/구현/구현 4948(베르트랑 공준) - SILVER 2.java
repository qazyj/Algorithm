import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
                
        while(n!=0) {
			int count = 0;			
			
			for(int i = n + 1; i <= n * 2; i++) {
				boolean flag = false;
				for(int j = 2; j < (int)Math.sqrt(i) + 1; j++) {
					if(i % j == 0) {
						flag = true;
						break;
					}
				}
				if(!flag) count++;
			}
			
			System.out.println(count);
			n = Integer.parseInt(br.readLine());
		}
		
	}
}
