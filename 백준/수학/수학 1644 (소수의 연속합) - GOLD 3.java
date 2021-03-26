import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
        ArrayList<Integer> primeNumber = new ArrayList<>();
		
        boolean[] prime = new boolean[(int) (N+1)];
        prime[0] = prime[1] = true;
        
        for(int i=2; i*i<=N; i++){
            if(!prime[i]) {
            	for(int j=i*i; j<=N; j+=i) 
            		prime[j]=true;      
            }
        }            
        for(int i=2; i<=N;i++){
        	if(!prime[i]) primeNumber.add(i);     
        }
        
        int start=0, end=0, sum=0, count=0;
        while(true) {
        	if(sum==N) {
        		count++;
        		sum-=primeNumber.get(start++);
        	}
        	else if(sum>N) {
        		sum-=primeNumber.get(start++);
        	}
        	else if(end == primeNumber.size()) { //sum이 N보다 작은데 end의 위치가 primeNumber.size랑 같으면 더이상 더해줄게 없기때문에 break;
        		break;
        	}
        	else {
        		sum+=primeNumber.get(end++);
        	}
        }
        
        System.out.println(count);
    }
}