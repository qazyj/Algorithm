import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		String s = "((100+1+)|(01))+";
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			if(temp.matches(s))
				sb.append("YES");
			else
				sb.append("NO");
			sb.append("\n");
		}

		System.out.print(sb);
	}
}