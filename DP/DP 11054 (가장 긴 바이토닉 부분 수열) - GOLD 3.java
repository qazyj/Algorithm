import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Algorithm {	
	static int N;
	static int[] inputArray;
	static int[] lis;
	static int[] lds;
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		N = Integer.parseInt(br.readLine());		
		lis = new int[N];	
		lds = new int[N];	
		inputArray = new int[N];		
 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < N; i++) 
			inputArray[i] = Integer.parseInt(st.nextToken());	
 
		LIS();
		LDS();
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(max < lis[i] + lds[i])  
				max = lis[i] + lds[i];			
		}
 
		System.out.println(max - 1);
	}
 
	
	
	static void LIS() { 
		for(int i = 0; i < N; i++) {
			lis[i] = 1;
			
			for(int j = 0; j < i; j++) {
				if(inputArray[j] < inputArray[i] && lis[i] < lis[j] + 1);				
			}
		}
	}
 
 
	
	static void LDS() {		
		for (int i = N - 1; i >= 0; i--) {			
			lds[i] = 1;			
			
			for (int j = N - 1; j > i; j--) {
				if (inputArray[j] < inputArray[i] && lds[i] < lds[j] + 1) 
					lds[i] = lds[j] + 1;	
			}
		}
	
	}
}