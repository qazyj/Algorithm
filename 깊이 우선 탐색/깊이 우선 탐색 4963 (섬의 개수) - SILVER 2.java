import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int[][] array;
	static boolean[][] check;
    static int[] x = {0,0,1,-1,-1,1,1,-1};
    static int[] y = {1,-1,0,0,1,1,-1,-1};
	static int N,M;
	static StringBuilder sb;

	public static void main(String args[]) throws Exception {
		SetData();
		System.out.print(sb);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		while(M != 0 && N != 0) {
			array = new int[N][M];
			check = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }

			int count = 0;
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < M; b++) {
					if (!check[a][b] && array[a][b] == 1) {
						count++;
						check[a][b] = true;
						bfs(a, b);
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			sb.append(count + "\n");
		}
	}

	private static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { a, b });

		while (!queue.isEmpty()) {
			int location[] = queue.poll();

			for (int direction = 0; direction < 8; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r >= 0 && c >= 0 && r < N && c < M) {
					if (array[r][c] == 1 && !check[r][c]) {
						queue.offer(new int[] { r, c });
						check[r][c] = true;
					}
				}
			}
		}
	}

}