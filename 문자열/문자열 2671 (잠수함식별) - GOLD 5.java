import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String sound = br.readLine();
		String check = "(100+1+|01)+";
		
		if(sound.matches(check))
			System.out.println("SUBMARINE");
		else
		    System.out.println("NOISE");
		
	}
}
