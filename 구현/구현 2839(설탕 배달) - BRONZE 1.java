import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = 0;
		n = Integer.parseInt(br.readLine());
		
		while(true) {
			if (n==0)
				break;
			
			if (n%5==0) 
				n-=5;
			else if (n%3==0)
				n-=3;
			else if (n>5)
				n-=5;
			else if (n>3)
				n-=3;
			else
				break;
			count++;
		}
		
		if(n!=0)
			System.out.println("-1");
		else
			System.out.println(count);
		
		
	}
}
