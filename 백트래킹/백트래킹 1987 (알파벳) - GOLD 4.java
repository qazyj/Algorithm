import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static int[][] array;
	static boolean[] check = new boolean[26];
	static int[] x = {0,1,0,-1};
	static int[] y = {1,0,-1,0};
	static int result = 0;

	public static void dfs(int a, int b, int count) {
		if(check[array[a][b]]) {
			result = Math.max(result, count);
			return;
		} else {
			check[array[a][b]] = true;
			for(int i = 0; i < 4; i++) {
				int r = a + x[i];
				int c = b + y[i];

				if(r >= 0 && c >= 0 && r < R && c < C) {
					dfs(r, c, count + 1);
				}

			}

			check[array[a][b]] = false;

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new int[R][C];
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				array[i][j] = s.charAt(j) - 'A';
			}
		}

		dfs(0, 0, 0);

		System.out.println(result);
	}
}
