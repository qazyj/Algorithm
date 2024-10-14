import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		while(n-->0) {
			String s = br.readLine();
			Set<Character> set = new HashSet<>();
			set.add(s.charAt(0));
			boolean flag = true;
			for(int i = 1; i < s.length(); i++) {
				if(set.contains(s.charAt(i))) {
					if(s.charAt(i) != s.charAt(i-1)) {
						flag = false;
						continue;
					}
				} else {
					set.add(s.charAt(i));
				}
			}
			if(flag) answer++;
		}
		System.out.println(answer);
	}
}