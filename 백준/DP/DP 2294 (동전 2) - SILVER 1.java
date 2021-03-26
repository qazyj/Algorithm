import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, S, value;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception {
		SetData();
		dp();
		System.out.println(value);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		coin = new int[101]; // 인덱스 번호 1부터 +1씩 해서 배열 선언
		dp = new int[10001];

		for (int i = 1; i <= N; i++)
			coin[i] = Integer.parseInt(br.readLine());

		Arrays.fill(dp, 10001);
		dp[0] = 0;
	}

	private static void dp() {
		// i는 x번째->coin[x번째 동전 종류]의 경우를 의미
		// j는 i의 동전 종류로 1~s원를 채우는 경우의 수를 의미
		for (int i = 1; i <= N; i++) {
			for (int j = coin[i]; j <= S; j++) {
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		
		if(dp[S] == 10001)
			value = -1;
		else
			value = dp[S];
	}
}
