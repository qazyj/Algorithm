import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int index = 0;
		int answer = 1;
		while(index < s.length()) {
			String cur = String.valueOf(answer);
			for(int i = 0; i < cur.length(); i++) {
				char ch = cur.charAt(i);
				if(index < s.length() && s.charAt(index) == ch) index++;
			}
			if(index >= s.length()) break;
			answer++;
		}
		System.out.println(answer);
	}
}