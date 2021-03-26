import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int array[] = new int[N+1];
		int dp[] = new int[N+1];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}

		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				// 앞에 자신보다 작은 상자가 있으면 dp[j]+1한 값이 현재 i 인덱스보다 클시 dp[i] 변경
				if (array[j] < array[i])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			// 현재 i 인덱스의 dp 값이 max값보다 크면 max값을 dp[i]로 변경
			max = Math.max(max, dp[i]);
		}

		// 기존에 있던 상자 + 1
		System.out.println(max+1);
	}
}