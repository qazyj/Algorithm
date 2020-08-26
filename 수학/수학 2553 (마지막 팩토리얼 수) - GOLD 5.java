import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long input = Long.parseLong(br.readLine());
		long m =1;
		
		for(int i=1;i<=input;i++) {
			m*=i;
			m %= 1000000000;
			while (m % 10 == 0)
			     m /= 10;
		}
		 System.out.println(m%10);
    }
}