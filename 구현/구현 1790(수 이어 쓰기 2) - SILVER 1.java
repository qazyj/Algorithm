import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
		long finalNum = 0;
		long length = 1;
		long numberOfDigits = 9;

		while (k > length * numberOfDigits) {
			k -= (length * numberOfDigits);
			finalNum += numberOfDigits;
			
			length++;
			numberOfDigits *=10;
		}
		
		if(k>numberOfDigits*length || N<k)
			System.out.println(-1);
		else
			;
		
		/*
		finalNum = (finalNum+1)+ (k-1)/length;
		if(finalNum > N) {
			System.out.println(-1);
		}else {
			int index = (int) ((k-1)%length);
			System.out.println(String.valueOf(finalNum).charAt(index));
		}*/
		
        
	}
}