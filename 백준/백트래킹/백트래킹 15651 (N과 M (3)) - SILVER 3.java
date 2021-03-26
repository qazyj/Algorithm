import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int list[];
	private static int n, m;
	private static StringBuilder sb;

	private static void dfs(int cnt) {
		if (cnt == m) {
			
			for (int i = 0; i < m; i++) {
				sb.append(list[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= n; i++) {
			list[cnt] = i;
			dfs(cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new int[9];

		sb = new StringBuilder();

		dfs(0);

		bw.write(sb.toString());

		bw.close();
		br.close();
	}

}