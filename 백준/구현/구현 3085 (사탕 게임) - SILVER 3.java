import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static char arr[][];

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = 0;
		arr = new char[N][N];
		for(int i = 0; i < N; i++) {
			String tmp = in.nextLine();
			for(int j = 0; j < N; j++) arr[i][j] = tmp.charAt(j);
			answer = Math.max(answer, checkRow(i));
		}

		for(int i = 0; i < N; i++) answer = Math.max(answer, checkColumn(i));

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(j + 1 < N) {
					swap(i, j, i, j + 1);
					answer = Math.max(answer, checkRow(i));
					answer = Math.max(answer, checkColumn(j));
					answer = Math.max(answer, checkColumn(j + 1));
					swap(i, j, i, j + 1);
				}
				if(i + 1 < N) {
					swap(i, j, i + 1, j);
					answer = Math.max(answer, checkRow(i));
					answer = Math.max(answer, checkRow(i + 1));
					answer = Math.max(answer, checkColumn(j));
					swap(i, j, i + 1, j);
				}
			}
		}

	}

	public static void swap(int x1, int y1, int x2, int y2) {
		char tmp = arr[x1][y1];
		arr[x1][y1] = arr[x2][y2];
		arr[x2][y2] = tmp;
	}

	public static int checkRow(int x) {
		int temp = 1, result = 1;
		char c = arr[x][0];
		for(int i = 1; i < N; i++) {
			if(arr[x][i] != c) {
				c = arr[x][i];
				result = Math.max(result, temp);
				temp = 1;
			} else temp++;
		}
		return Math.max(result,  temp);
	}

	public static int checkColumn(int y) {
		int temp = 1, result = 1;
		char c = arr[0][y];
		for(int i = 1; i < N; i++) {
			if(arr[i][y] != c) {
				c = arr[i][y];
				result = Math.max(result, temp);
				temp = 1;
			} else temp++;
		}
		return Math.max(result, temp);
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