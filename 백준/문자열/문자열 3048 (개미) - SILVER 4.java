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

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		String s1 = br.readLine();
		String s2 = br.readLine();
		int count = Integer.parseInt(br.readLine());

		char[] s = new char[a+b];
		boolean[] d = new boolean[a+b];

		for(int i = 0; i < a; i++) {
			s[i] = s1.charAt(a-1-i);
			d[i] = true;
		}

		for(int i = a; i < a+b; i++) {
			s[i] = s2.charAt(i-a);
			d[i] = false;
		}

		for(int i = 0; i < count; i++) {
			for(int j = 0; j < a+b-1; j++) {
				if(!d[j] || d[j+1]) continue;

				char temp = s[j];
				s[j] = s[j+1];
				s[j+1] = temp;

				boolean flag = d[j];
				d[j] = d[j+1];
				d[j+1] = flag;
				j++;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length; i++) {
			sb.append(s[i]);
		}
		System.out.println(sb);
	}
}