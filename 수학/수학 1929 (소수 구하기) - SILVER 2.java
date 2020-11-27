import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M;
	static boolean[] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		FindDecimal();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		check = new boolean[M + 1];
		check[0] = check[1] = true;

	}

	private static void FindDecimal() {
		for (int i = 2; i <= M; i++) {
			if (check[i] == true) {
				continue;
			}
			for (int j = i + i; j <= M; j = j + i) {
				check[j] = true;
			}
		}
		for (int i = N; i <= M; i++) {
			if (check[i] == false)
				sb.append(i + "\n");
		}

	}
}
