import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] array;
	static boolean[][] check;
	static int R, C;
	static int[] x = { -1, 0, 1 };
	static int[] y = { 1, 1, 1 };

    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		array = new int[R][C];
		check = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				array[i][j] = s.charAt(j);
			}
		}
		
		int count = 0;
		for (int i = 0; i < R; i++) 
			count += bfs(i, 0);

		System.out.print(count);
	}

	public static int bfs(int a, int b) {
		if (b == C - 1) 
			return 1;

		for (int direction = 0; direction < 3; direction++) {
			int r = a + x[direction];
			int c = b + y[direction];

			if (r >= 0 && c >= 0 && r < R && c < C) {
				if (array[r][c] == '.' && !check[r][c]) {
					check[r][c] = true;
					if(bfs(r, c) == 1) return 1;
				}
			}
		}
		return 0;
	}
}
