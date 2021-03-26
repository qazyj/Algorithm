import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int L, P, V, count;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		count = 1;

		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			if (L == 0)			break;
			// V/P = 연속
			sb.append("Case " + count + ": " + ((V / P) * L + Math.min(L, V % P)) + "\n");
			count++;
		}
	}
}
