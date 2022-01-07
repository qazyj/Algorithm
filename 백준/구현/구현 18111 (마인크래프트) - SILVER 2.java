import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int time, height;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(time + " " + height);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		int M = in.nextInt();
		int B = in.nextInt();
		time = Integer.MAX_VALUE;
		height = -1;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();

				min = Math.min(min, array[i][j]);
				max = Math.max(max, array[i][j]);
			}
		}

		for (int i = min; i <= max; i++) { // 최소층 부터 최대층 까지

			int seconds = 0;
			int inventory = B;

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					int diff = array[j][k] - i;

					if (diff > 0) { // 제거해야 함
						seconds += diff * 2;
						inventory += diff;
					} else if (diff < 0) { // 추가해야 함
						seconds += Math.abs(diff);
						inventory -= Math.abs(diff);
					}
				}
			}

			if (inventory < 0)	continue;
			if (seconds <= time) { // == 가 포함되어야 함 ㅇ그렇지 않으면 최대 높이를 판별 하지 못함
				time = seconds;
				height = i;
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