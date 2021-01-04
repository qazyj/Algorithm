import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://qazyj.tistory.com/71

public class Main {
	static int M, N;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static int[][] array;
	static boolean[][] check;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());

		for (int i = 0; i < testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			array = new int[M][N];
			check = new boolean[M][N];
			
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				array[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}

			int count = 0;
			for (int a = 0; a < M; a++) {
				for (int b = 0; b < N; b++) {
					if (!check[a][b] && array[a][b] == 1) {
						count++;
						check[a][b] = true;
						bfs(a, b);
					}
				}
			}
			
			sb.append(count + "\n");
		}
	}
	
	private static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { a, b });

		while (!queue.isEmpty()) {
			int location[] = queue.poll();

			for (int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r >= 0 && c >= 0 && r < M && c < N) {
					if (array[r][c] == 1 && !check[r][c]) {
						queue.offer(new int[] { r, c });
						check[r][c] = true;
					}
				}
			}
		}
	}
}
