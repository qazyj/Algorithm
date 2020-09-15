import java.io.*;

public class Main {
	static boolean check[][];
	static int result = 0, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		check = new boolean[N][N];
		dfs(0);
		System.out.println(result);
	}

	static void dfs(int x) {
		if(x == N) {
			result++;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(QueenCheck(x, i)) {
				check[x][i] = true;
				dfs(x + 1);
				check[x][i] = false;
			}
		}
	}

	static boolean QueenCheck(int x, int y) {
		
		// 위로
		for(int i = x; i >= 0; i--)
			if(check[i][y])
				return false;
		
		// 왼쪽 위로 대각선
		int i = x - 1;
		int j = y - 1;
		while(i >= 0 && j >= 0)
			if(check[i--][j--])
				return false;
		
		// 오른쪽 위로 대각선
		i = x - 1;
		j = y + 1;
		while(i >= 0 && j < N)
			if(check[i--][j++])
				return false;
		
		return true;
	}
}
