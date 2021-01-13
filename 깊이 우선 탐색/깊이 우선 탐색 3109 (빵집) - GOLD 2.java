import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int R, C, answer;
	static int[][] array;
	static boolean[][] check;
	static int[] x = { -1, 0, 1 };
	static int[] y = { 1, 1, 1 };
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.print(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		answer = 0;

		array = new int[R][C];
		check = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				array[i][j] = s.charAt(j);
			}
		}
	
		for (int i = 0; i < R; i++) 
			answer += dfs(i, 0);
	}
	
	public static int dfs(int a, int b) {
		if (b == C - 1) 
			return 1;

		for (int direction = 0; direction < 3; direction++) {
			int r = a + x[direction];
			int c = b + y[direction];

			if (r >= 0 && c >= 0 && r < R && c < C) {
				if (array[r][c] == '.' && !check[r][c]) {
					check[r][c] = true;
					if(dfs(r, c) == 1) return 1;
				}
			}
		}
		return 0;
	}
}
