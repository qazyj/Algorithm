import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int input = Integer.parseInt(br.readLine());
		if(input==1) {
			System.out.println(2);
			System.exit(0);
		}

		while(true) {
					
			if(isDecimal(input) && isPalindrome(input)) {
				System.out.println(input);
				break;
			}			
			input++;
		}
	}
	
	static boolean isDecimal(int number) {
		for(int i=2;i<=(int)Math.sqrt(number);i++) {
			if(number%i==0) 
				return false;
		}
		return true;
	}
	
	
	static boolean isPalindrome(int number) {
		char [] integerToCharArray = String.valueOf(number).toCharArray();
		int length = integerToCharArray.length-1;
		for(int i=0;i<=integerToCharArray.length/2;i++) {
			if(integerToCharArray[i] != integerToCharArray[length--]) 
				return false;
		}
		return true;
	}
}