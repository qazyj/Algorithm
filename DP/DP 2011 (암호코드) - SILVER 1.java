import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static String N;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		SaveMaxValue();
		System.out.println(dp[N.length()] % 1000000);
	}

	// 데이터
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = br.readLine();
		
		// 0으로 시작하는 경우 암호를 해석 할 수 없기 때문에 0을 출력하고 종료해야 된다.
		// 이 부분을 생각 안하고 있다가 애먹었다.
		if (N.charAt(0) == '0') {
			System.out.println(0);
			System.exit(0);
		}

		dp = new long[N.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
	}

	private static void SaveMaxValue() {
		for (int i = 1; i < N.length(); i++) {
			int previousNumber = N.charAt(i - 1) - '0';
			int currentNumber = N.charAt(i) - '0';
			// 1 글자만
			if (currentNumber >= 1 && currentNumber <= 9) {
				dp[i + 1] += dp[i];
			}
			// 2 글자도 가능
			if (!(previousNumber == 0 || previousNumber > 2 || (previousNumber == 2 && currentNumber > 6))) {
				dp[i + 1] += dp[i - 1];
			}

			dp[i + 1] %= 1000000;
		}
	}

}