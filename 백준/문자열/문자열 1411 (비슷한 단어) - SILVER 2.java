import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	static int n, answer;
	static int[][] arr;
	static int R,G,B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] s = new String[n];
		for(int i =0 ; i < n; i++) {
			s[i] = br.readLine();
		}

		int length = s[0].length();
		int answer = 0;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				Map<Character, Character> map = new HashMap<>();
				boolean check = false;
				for(int k = 0; k < length; k++) {
					if(map.containsKey(s[i].charAt(k))) {
						if(map.get(s[i].charAt(k)) != s[j].charAt(k)) {
							check = true;
							break;
						}
						continue;
					}
					if(map.containsValue(s[j].charAt(k))) {
						check = true;
						break;
					}
					map.put(s[i].charAt(k), s[j].charAt(k));
				}
				if(check) continue;
				answer++;
			}
		}
		System.out.println(answer);
	}
}