import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int answer;
	static int MOD = 1000000003;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData()throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		int K = in.nextInt();

		int[][] dp = new int[N + 1][N + 1];

		for(int i = 1; i <= N; i++){
			// i개 중에서 1개를 선택하는 방법은 i개
			dp[i][1] = i;
			// 0개를 선택한 경우는 1로 초기화
			// 점화식을 위해서는 1로 초기화
			dp[i][0] = 1;
		}

		// i가 3미만인 경우는 계산할 필요가 없음
		for(int i = 3; i <= N; i++){
			// n개의 수 중 n/2개 보다 더 많이 고르는 경우는 0가지
			// 그렇기 때문에 j의 범위를 다음과 같이 설정
			for(int j = 2; j <= (i + 1) / 2; j++){
				// i번째 색을 선택하지 않은 경우와 선택한 경우
				dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
			}
		}

		// n번째 색을 선택한 경우 + n번째 색을 선택하지 않은 경우
		answer = (dp[N - 3][K - 1] + dp[N - 1][K]) % MOD;
	}
}


class InputReader {
	private final InputStream stream;
	private final byte[] buf = new byte[8192];
	private int curChar, snumChars;

	public InputReader(InputStream st) {
		this.stream = st;
	}

	public int read() {
		if (snumChars == -1)
			throw new InputMismatchException();
		if (curChar >= snumChars) {
			curChar = 0;
			try {
				snumChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (snumChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public long nextLong() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public int[] nextIntArray(int n) {
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		return a;
	}

	public String nextLine() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isEndOfLine(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	private boolean isEndOfLine(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}
}