import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int[][] array;
	static int N, M, H, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		H = in.nextInt();
		array = new int[H + 1][N];
		answer = -1;

		for (int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			array[a][b] = 1;
			array[a][b + 1] = -1;
		}

		if (searchOddNumber() > 3)	return;

		for (int i = 0; i <= 3; i++)
			if(dfs(0, 0, 0, i)) break;
	}

	private static boolean dfs(int x, int y, int cnt, int size) {
		if (cnt == size) {
			if (checkLadder()) {
				answer = size;
				return true;
			}
			return false;
		}

		for (int i = x; i < H; i++) {
			for (int j = y; j < N - 1; j++) {
				if (array[i][j] != 0 || array[i][j + 1] != 0)
					continue;

				array[i][j] = 1;
				array[i][j + 1] = -1;
				if (dfs(i, j + 2, cnt + 1, size))
					return true;
				array[i][j] = 0;
				array[i][j + 1] = 0;
			}
			y = 0;
		}
		return false;
	}

	private static boolean checkLadder() {
		for (int j = 0; j < N; j++) {
			int r = 0, c = j;

			while (r <= H) {
				if (array[r][c] == 1)
					c++;
				else if (array[r][c] == -1)
					c--;
				r++;
			}
			if (c != j)
				return false;
		}
		return true;
	}

	private static int searchOddNumber() {
		int oddNumber = 0;
		for (int j = 0; j < N - 1; j++) {
			int num = 0;
			for (int i = 0; i < H; i++)
				if (array[i][j] == 1)
					num++;
			if (num % 2 == 1)
				oddNumber++;
		}
		return oddNumber;
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