import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Algorithm {	
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int n = s.length(), max = 0;
		
		for(int i = 0; i < n; i++) 
			max = Math.max(max, KMP(s.substring(i, n)));
		
		System.out.println(max);
	}

	// KMP 알고리즘 - pi[] 배열에서 주어진 문자열의 인덱스 0부터 i까지의 부분 문자열 중
	// 접두사 == 접미사가 되는 가장 긴 접두사의 길이를 넣는다.
	// 현재 밑 코드에서 접두사는 j(왼쪽), 접미사는 i(오른쪽)이다.
	static int KMP(String s) {
		// j 접두사
		int j = 0, n = s.length(), max = 0;
		int pi[] = new int[n];
		
		// i 접미사
		for(int i = 1; i < n; i++) {
			while(j > 0 && s.charAt(i) != s.charAt(j)) 
				j = pi[j-1];
			
			if(s.charAt(i) == s.charAt(j)) 
				max = Math.max(max, pi[i] = ++j);
		}
		return max;
	}
}