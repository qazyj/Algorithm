import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int T, N;
	static int[] input;
	static int[][][] array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		T = in.nextInt();
		sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			N = in.nextInt();

			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = in.nextInt();
			}

			array = new int[2][N][N];
			sb.append(SaveAnswer(0, 0, N-1) + "\n");
		}
	}
	
	static int SaveAnswer(int who, int left, int right) {
		// basecase
		if (left == right) {
			if (who == 0) {
				return array[who][left][right] = input[left];
			}
			else {
				return array[who][left][right] = 0;
			}
		}
		
		if (array[who][left][right] != 0)
			return array[who][left][right];

		// 근우
		if (who == 0) {
			array[who][left][right] = Math.max(SaveAnswer(1, left + 1, right) + input[left], SaveAnswer(1, left, right - 1) + input[right]);
		}
		else {		// 명우
			array[who][left][right] = Math.min(SaveAnswer(0, left + 1, right), SaveAnswer(0, left, right - 1));
		}
		return array[who][left][right];
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