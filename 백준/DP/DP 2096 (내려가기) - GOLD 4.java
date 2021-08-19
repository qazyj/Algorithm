import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static int N;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.print(dp[N-1][5] + " " + dp[N-1][0]);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		N =  in.nextInt();
		int[] input = new int[3];
		dp = new int[N][6];

		for (int i = 0; i < 3; i++)
			input[i] =  in.nextInt();

		for (int i = 0; i < 3; i++)
			dp[0][3 + i] = dp[0][i] = input[i];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++)
				input[j] =  in.nextInt();

			int preIndex = i - 1;

			dp[i][0] = Integer.min(dp[preIndex][0], dp[preIndex][1]) + input[0];
			dp[i][1] = Integer.min(dp[preIndex][0], Math.min(dp[preIndex][1], dp[preIndex][2])) + input[1];
			dp[i][2] = Integer.min(dp[preIndex][1], dp[preIndex][2]) + input[2];

			dp[i][3] = Integer.max(dp[preIndex][3], dp[preIndex][4]) + input[0];
			dp[i][4] = Integer.max(dp[preIndex][3], Math.max(dp[preIndex][4], dp[preIndex][5])) + input[1];
			dp[i][5] = Integer.max(dp[preIndex][4], dp[preIndex][5]) + input[2];
		}

		Arrays.sort(dp[N - 1]);
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