import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int N, count, colorWeeknessCount;
	static char[][] array;
	static boolean[][] check;
	static boolean colorWeakness;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.print(count + " " + colorWeeknessCount);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		array = new char[N][N];
		check = new boolean[N][N];
		colorWeakness = false;
		count = 0;
		colorWeeknessCount = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				array[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					bfs(i, j, array[i][j]);
					count++;
				}
			}
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				check[i][j] = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i][j] == 'G')
					array[i][j] = 'R';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					bfs(i, j, array[i][j]);
					colorWeeknessCount++;
				}
			}
		}
	}
	
	private static void bfs(int a, int b, char temp) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { a, b });

		while (!queue.isEmpty()) {
			int location[] = queue.poll();

			for (int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r >= 0 && c >= 0 && r < N && c < N) {
					if (array[r][c] == temp && !check[r][c]) {
						check[r][c] = true;
						queue.offer(new int[] { r, c });
					}
				}
			}
		}
	}
}