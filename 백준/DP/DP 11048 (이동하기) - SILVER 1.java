import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] array = new int[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				array[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) 
				array[i][j] += Math.max(array[i-1][j-1], Math.max(array[i-1][j], array[i][j-1]));
		}
		
		System.out.println(array[N][M]);
	}

}