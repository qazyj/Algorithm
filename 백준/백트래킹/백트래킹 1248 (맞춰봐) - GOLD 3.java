import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N;
	static char[][] array;
	static int[] data;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		sb = new StringBuilder();
		String s = in.nextLine();
		array = new char[N][N];
		data = new int[N];

		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				array[i][j] = s.charAt(index++);
			}
		}

		dfs(0);

	}

	private static void dfs(int index) {
		if (index == N) {
			for (int i = 0; i < N; i++)
				sb.append(data[i] + " ");
			System.out.println(sb);
			System.exit(0);
		}

		for (int i = -10; i <= 10; i++) {
			data[index] = i;
			if (check(index)) {
				dfs(index + 1);
			}
		}
	}

	private static boolean check(int index) {
		for (int i = 0; i <= index; i++) {
			int sum = 0;
			for (int j = i; j <= index; j++) {
				sum += data[j];
				if(array[i][j] == '0' && sum != 0) {
					return false;
				} else if(array[i][j] == '-' && sum >= 0) {
					return false;
				} else if(array[i][j] == '+' && sum <= 0) {
					return false;
				}
			}
		}
		return true;
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