import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Algorithm {
	static int N, dice[][], answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);
		
		N = in.nextInt();
		dice = new int[N][6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 6; j++) {
				if (j == 3)
					dice[i][4] = in.nextInt();
				else if (j == 4)
					dice[i][3] = in.nextInt();
				else
					dice[i][j] = in.nextInt();
			}
		}

		// AFBDCE
		// 051324
		for (int i = 0; i < 6; i++)
			dfs(dice[0][i], 0, 0);
	}

	static void dfs(int down, int index, int sum) {
		if (index == N) {
			answer = Math.max(sum, answer);
			return;
		}
		if (sum + (N - index) * 6 <= answer) {
			return;
		}
		
		int up = 0;
		for (int i = 0; i < 6; i++) {
			if (dice[index][i] == down) {
				up = dice[index][5 - i];
				break;
			}
		}
		
		int maxside = 0;
		for (int i = 0; i < 6; i++) {
			if (dice[index][i] == down || dice[index][i] == up) {
				continue;
			}
			maxside = Math.max(maxside, dice[index][i]);
		}
		dfs(up, index + 1, sum + maxside);

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