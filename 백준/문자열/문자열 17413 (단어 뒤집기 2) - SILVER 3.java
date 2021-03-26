import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Algorithm {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String temp = "";
		StringBuilder sb = new StringBuilder();

		boolean check = false;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '<')		// 뒤집지 않는 시작 문자 
				check = true;
			
			if(check)			// 뒤집지 않아야할 문자열
				temp = temp + s.charAt(i);
			else {				// 뒤집어야할 문자열
				if(s.charAt(i) == ' ')
					temp = temp + s.charAt(i);
				else
					temp = s.charAt(i) + temp;
			}
			
			if(s.charAt(i) == '>')		// 뒤집지 않는 끝 문자
				check = false;
			
			// ' ' or '>' or 문자열 끝이면 append
			if(s.charAt(i) == '>' || s.charAt(i) == ' ' || i == s.length()-1) {
				sb.append(temp);
				temp = "";
			}
			
		}
		System.out.println(sb);
	}
}
