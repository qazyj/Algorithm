import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int N;
    static boolean[] flagRow;
    static boolean[] flagDiag1;
    static boolean[] flagDiag2;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(dfs(0));
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
        flagRow = new boolean[N];
        flagDiag1 = new boolean[2 * N - 1];
        flagDiag2 = new boolean[2 * N - 1];
	}
	
    private static int dfs(int depth) {
        if (depth == N) return 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (!flagRow[i] && !flagDiag1[depth + i] && !flagDiag2[depth - i + N - 1]) {
                flagRow[i] = flagDiag1[depth + i] = flagDiag2[depth - i + N - 1] = true;
                sum += dfs(depth + 1);
                flagRow[i] = flagDiag1[depth + i] = flagDiag2[depth - i + N - 1] = false;

            }
        }
        return sum;
    }
}