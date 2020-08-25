import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		long number = Long.parseLong(br.readLine());
		int p = (int)(number % 1500000);
		
		long[] fibonacciArray = new long[p+1];
		
		fibonacciArray[0]=0;
		fibonacciArray[1]=1;
		
		
		for (int i = 2; i <= p; i++) { 
			fibonacciArray[i] = (fibonacciArray[i-1] + fibonacciArray[i-2])%(long)Math.pow(10,6);
		}
		
		System.out.println(fibonacciArray[p]);

		
		//for(int i=0;i<number;i++)
		//	System.out.println(Fibonacci(i));
		
    }
 
    public static long Fibonacci(long a) {
        if(a<=1)
        	return a;
        else
        	return Fibonacci(a-1) + Fibonacci(a-2);
    }
}
