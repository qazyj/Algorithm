import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int M, N, count, answer;
	static char[][] array;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static boolean[][] check;
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		SetData();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i][j] == 'L') {

					bfs(i, j);
					check = new boolean[M][N];
				}
				answer = Math.max(count, answer);
				count = 0;
			}
		}
		
		System.out.println(answer - 1);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		array = new char[M][N];
		check = new boolean[M][N];
		queue = new LinkedList<>();
		count = 0;
		answer = 0;

		for (int i = 0; i < M; i++) {
			String ss = br.readLine();
			for (int j = 0; j < N; j++) {
				array[i][j] = ss.charAt(j);
			}
		}
	}

	public static void bfs(int i, int j) {
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			int len = queue.size();
			count++;

			for (int l = 0; l < len; l++) {

				int location[] = queue.poll();
				check[location[0]][location[1]] = true;

				for (int direction = 0; direction < 4; direction++) {
					int r = location[0] + x[direction];
					int c = location[1] + y[direction];

					if (r >= 0 && r < M && c >= 0 && c < N) {
						if (!check[r][c] && array[r][c] == 'L') {
							queue.offer(new int[] { r, c });
							check[r][c] = true;
						}
					}
				}
			}
		}
	}
}