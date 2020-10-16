import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	private static int N, M;
	private static int[][] array, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		array = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(bfs(0,0));
	}

	// 메모리 초과때문에 메모리 줄이기 위함
	private static int[][] xy = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	// 메모리 초과 때문에 bfs를 queue에서 재귀로 바꿈
	private static int bfs(int a, int b) {
		// base case
		if (a == M - 1 && b == N - 1)	return 1;
		if(dp[a][b] != -1)	 return dp[a][b];
		dp[a][b] = 0;
		
		for (int[] direction : xy) {
			int r = a + direction[0];
			int c = b + direction[1];

			if (r >= 0 && c >= 0 && r < M && c < N) {
				// 값이 작아질때만 queue에 추가
				if (array[a][b] > array[r][c]) {
					dp[a][b] += bfs(r, c);
					
				}
			}

		}
		return dp[a][b];
	}
}
