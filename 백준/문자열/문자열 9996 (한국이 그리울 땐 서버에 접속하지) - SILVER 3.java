import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split("\\*");
		String pre = input[0];
		String post = input[1];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			if(pre.length()+post.length()<=s.length() && s.startsWith(pre) && s.endsWith(post)) {
				sb.append("DA").append("\n");
			} else {
				sb.append("NE").append("\n");
			}
		}
		System.out.println(sb);
	}
}