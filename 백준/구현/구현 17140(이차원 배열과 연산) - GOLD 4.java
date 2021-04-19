import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int r, c, k, answer, maxR, maxC;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		r = in.nextInt()-1;
		c = in.nextInt()-1;
		k = in.nextInt();
		maxR = 3;
		maxC = 3;
		array = new int[101][101];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = in.nextInt();
			}
		}

		solve();
		if (answer == 101)
			answer = -1;
	}

	private static void solve() {
		while (array[r][c] != k && answer <= 100) {
			answer++;

			if (maxR >= maxC)
				CalculationRow();
			else
				CalculationColumn();
		}
	}

	private static void CalculationRow() {
		int max = 0;
		for (int i = 0; i < maxR; i++) {
			int[] count = new int[101];
			for (int j = 0; j < maxC; j++) {
				count[array[i][j]]++;
			}
			int l = 0;
			for (int j = 1; j <= maxC && l < 100; j++) {
				for (int z = 1; z < 101; z++) {
					if (count[z] == j) {
						array[i][l++] = z;
						array[i][l++] = j;
					}
				}
			}
			for (int j = l; j <= maxC && j < 100; j++) {
				array[i][j] = 0;
			}
			max = Math.max(max, l);
		}
		maxC = max;
	}

	private static void CalculationColumn() {
		int max = 0;
		for (int j = 0; j < maxC; j++) {
			int count[] = new int[101];
			for (int i = 0; i < maxR; i++) {
				count[array[i][j]]++;
			}
			int l = 0;
			for (int i = 1; i <= maxR; i++) {
				for (int z = 1; z < 101; z++) {
					if (count[z] == i) {
						array[l++][j] = z;
						array[l++][j] = i;
					}
				}
			}
			for (int i = l; i <= maxR && i < 100; i++) {
				array[i][j] = 0;
			}
			max = l > max ? l : max;
		}
		maxR = max;
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