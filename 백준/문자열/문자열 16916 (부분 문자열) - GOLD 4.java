import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {	
	static String firstString, secondString;
	static int pi[];
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		firstString = br.readLine();
		secondString = br.readLine();
		pi = new int[secondString.length()];
				
		System.out.println(KMP());
	}

	// KMP 알고리즘 - pi[] 배열에서 주어진 문자열의 인덱스 0부터 i까지의 부분 문자열 중
	// 접두사 == 접미사가 되는 가장 긴 접두사의 길이를 넣는다.
	// 현재 밑 코드에서 접두사는 j(왼쪽), 접미사는 i(오른쪽)이다.
	static int KMP() {
		// j 접두사
		int j = 0;
		
		// i 접미사
	    for (int i = 1; i < secondString.length(); ++i) {
	        while (j > 0 && secondString.charAt(i) != secondString.charAt(j)) j = pi[j - 1];
	        if (secondString.charAt(i) == secondString.charAt(j)) pi[i] = ++j;
	    }
	    
	    j = 0;
	    for (int i = 0; i < firstString.length(); ++i) {
	        while (j > 0 && firstString.charAt(i) != secondString.charAt(j)) j = pi[j - 1];
	        if (firstString.charAt(i) == secondString.charAt(j)) {
	            if (j == secondString.length() - 1) {
	                return 1;
	            }
	            else ++j;
	        }
	    }
	    return 0;
	}
}