import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static double percentage;	
	static double[] percent;	// 각 방향으로 이동할 확률
	static boolean[][] check;	// 단순 경로인지 아닌지 체크
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		percent = new double[4];
		check = new boolean[30][30];
		percentage = 0.0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// 입력값을 확률로 바꿈
		for (int i = 0; i < 4; i++) 
			percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
		
		check[14][14] = true;
		dfs(14, 14, 0, 1.0);
		
		System.out.println(percentage);
	}

	private static void dfs(int a, int b, int count, double per) {
		if (count == N) {
			percentage += per;
			return;
		}

		// 4방향 이동
		for (int i = 0; i < 4; i++) {
			int r = a + x[i];
			int c = b + y[i];
			if (!check[r][c]) {
				check[r][c] = true;
				dfs(r, c, count + 1, per * percent[i]);
				check[r][c] = false;
			}
		}
	}
}