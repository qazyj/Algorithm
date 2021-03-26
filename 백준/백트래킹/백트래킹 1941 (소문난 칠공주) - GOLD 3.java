import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	static char[][] array;
	static boolean[] check;
	static int[] x = { -1, 0, 1, 0 };
	static int[] y = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		answer = 0;
		array = new char[5][5];
		check = new boolean[1 << 25];
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				array[i][j] = s.charAt(j);
				if (array[i][j] == 'S')
					queue.offer(new int[] { i, j });
			}
		}

		while (!queue.isEmpty()) {
			int location[] = queue.poll();
			check[1 << (location[0] * 5 + location[1])] = true;
			dfs(1, 1, 1 << (location[0] * 5 + location[1]));
		}
	}

	private static void dfs(int count, int lee, int mark) {
		// basecase
		if (count == 7) {
			if (lee >= 4) {
				answer++;
			}
			return;
		}
		for (int i = 0; i < 25; i++) {
			if ((mark & (1 << i)) == 0)
				continue; // 현재 경로찾기

			for (int k = 0; k < 4; k++) {
				int r = i / 5 + x[k];
				int c = i % 5 + y[k];

				if (r < 0 || r >= 5 || c < 0 || c >= 5)		continue;
				
				int number = r * 5 + c;
				if (check[mark | (1 << number)]) 	continue; // 이미 방문했다면

				check[mark | (1 << number)] = true;
				if (array[r][c] == 'S')
					dfs(count + 1, lee + 1, mark | (1 << (number)));
				if (array[r][c] == 'Y')
					dfs(count + 1, lee, mark | (1 << (number)));
			}
		}
	}
}
