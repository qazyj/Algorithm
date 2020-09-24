import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M;
	static int[][] array;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		array = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0, count = SeparateNumber();

		while(count < 2) {
			if (count == 0) {
				ans = 0;
				break;
			}

			Melt();
			ans++;
		}

		System.out.println(ans);
	}

	private static int SeparateNumber() {
		boolean[][] check = new boolean[N][M];

		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(array[i][j] != 0 && !check[i][j]) {
					dfs(i, j, check);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(int a, int b, boolean[][] check) {
		check[a][b] = true;

		for(int i = 0; i < 4; i++) {
			int r = a + x[i];
			int c = b + y[i];

			if(r >= 0 && c >= 0 && r < N && c < M) {
				if(array[r][c] != 0 && !check[r][c]) {
					dfs(r, c, check);
				}
			}
		}
	}

	private static void Melt() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] check = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(array[i][j] != 0) {
					queue.offer(new int[] { i, j });
					check[i][j] = true;
				}
			}
		}

		while(!queue.isEmpty()) {
			int location[] = queue.poll();

			int seaNumber = 0;

			for(int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if(r >= 0 && c >= 0 && r < N && c < M) {
					if(!check[r][c] && array[r][c] == 0) {
						seaNumber++;
					}
				}
			}

			if(array[location[0]][location[1]] - seaNumber < 0) {
				array[location[0]][location[1]] = 0;
			} else {
				array[location[0]][location[1]] -= seaNumber;
			}
		}
	}
}
