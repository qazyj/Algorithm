import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int k = in.nextInt();
		int n = in.nextInt();
		sb = new StringBuilder();

		char[] startChar = new char[k];
		for (int i = 0; i < k; i++) {
			startChar[i] = (char) ('A' + i);
		}
		char[] destinationChar = in.nextLine().toCharArray();

		char[][] map = new char[n][k - 1];
		int lineIdx = 0;
		for (int i = 0; i < n; i++) {
			map[i] = in.nextLine().toCharArray();

			if (map[i][0] == '?')
				lineIdx = i;
		}

		for (int i = 0; i < lineIdx; i++) {
			for (int j = 0; j < k - 1; j++) {
				if (map[i][j] == '-') {
					char tmp = startChar[j];
					startChar[j] = startChar[j + 1];
					startChar[j + 1] = tmp;
				}
			}
		}

		for (int i = n - 1; i > lineIdx; i--) {
			for (int j = 0; j < k - 1; j++) {
				if (map[i][j] == '-') {
					char tmp = destinationChar[j];
					destinationChar[j] = destinationChar[j + 1];
					destinationChar[j + 1] = tmp;
				}
			}
		}

		for (int i = 0; i < k - 1; i++) {
			if (startChar[i] == destinationChar[i]) {
				sb.append("*");
			}

			else if (startChar[i] == destinationChar[i + 1] || startChar[i + 1] == destinationChar[i]) {
				sb.append("-");
				char tmp = startChar[i];
				startChar[i] = startChar[i + 1];
				startChar[i + 1] = tmp;
			}

			else {
				for (int j = 0; j < k - 1; j++)
					System.out.print("x");
				System.out.println();

				System.exit(0);
			}
		}
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