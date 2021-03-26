import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static boolean[] check;
	static int[] array;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			array = new int[N + 1];
			check = new boolean[N + 1];
			for (int i = 0; i < N; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0);
			System.out.println();
		}

	}

	public static void dfs(int startIndex, int depth) {
		if (depth == 6) {
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					System.out.print(array[i] + " ");
				}
			}
			System.out.println();
		}

		for (int i = startIndex; i < N; i++) {
			check[i] = true;
			dfs(i + 1, depth + 1);
			check[i] = false;
		}

	}
}
