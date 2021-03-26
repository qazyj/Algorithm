import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M, K;
	static int[][] array;
	static boolean[][] check;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		check = new boolean[N][M];
		sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int leftX = Integer.parseInt(st.nextToken()); // 왼쪽 위 x
			int leftY = Integer.parseInt(st.nextToken()); // 왼쪽 위 y
			int rightX = Integer.parseInt(st.nextToken()); // 오른쪽 아래 x
			int rightY = Integer.parseInt(st.nextToken()); // 오른쪽 아래 y
			for (int a = leftY; a < rightY; a++) {
				for (int b = leftX; b < rightX; b++) {
					array[a][b] = 1;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!check[i][j] && array[i][j] == 0) {
					int data = bfs(i, j);
					list.add(data);
					count++;
				}
			}
		}

		System.out.println(count);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + " ");
		}
	}

	private static int bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] { a, b });
		check[a][b] = true;
		int count = 1;

		while (!queue.isEmpty()) {
			int[] location = queue.poll();

			for (int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r < 0 || c < 0 | r >= N || c >= M)	continue;

				if (!check[r][c] && array[r][c] == 0) {
					check[r][c] = true;
					queue.offer(new int[] { r, c });
					count++;
				}

			}
		}
		return count;
	}
}
