import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, result;
    static int[][] array;
	static Boolean[][] check;
	static int[]x = {0, 0, 1, -1};
	static int[]y = {-1, 1, 0, 0};

	static int [][]shapeX = {{ 0, 0, 0, 1 }, { 0, 1, 2, 1 }, { 0, 0, 0, -1 }, { 0, -1, 0, 1 }};
	static int [][]shapeY = {{ 0, 1, 2, 1 }, { 0, 0, 0, 1 }, { 0, 1, 2, 1 }, { 0, 1, 1, 1 }};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		array = new int[N][M];
		check = new Boolean[N][M];
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++) 
				check[i][j] = false;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check[i][j] = true;
				dfs(i, j, array[i][j], 1);
				check[i][j] = false;
				shape(i, j);
			}
		}
		
		System.out.println(result);
	}

	public static void dfs(int a, int b, int sum, int length) {
		if (length >= 4) {
			result = Math.max(result, sum);
			return;
		}

		for (int direction = 0; direction < 4; direction++) {
			int r = a + x[direction];
			int c = b + y[direction];

			if (r >= 0 && c >= 0 && r < N && c < M) {
				if (!check[r][c]) {
					check[r][c] = true;
					dfs(r, c, sum + array[r][c], length + 1);
					check[r][c] = false;
				}
			}
		}
	}

	public static void shape(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int sum = 0;

			for (int j = 0; j < 4; j++) {
				int r = x + shapeX[i][j];
				int c = y + shapeY[i][j];

				if (r >= 0 && c >= 0 && r < N && c < M) {
					sum += array[r][c];
				}
			}
			
			result = Math.max(result, sum);
		}
	}
}