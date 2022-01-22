import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] array;
	static boolean[][] check;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int sheep = 0, wolf = 0;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int answerSheep = 0, answerWolf = 0;

		R = in.nextInt();
		C = in.nextInt();
		sb = new StringBuilder();
		array = new char[R][C];
		check = new boolean[R][C];
		for (int x = 0; x < R; x++) {
			array[x] = in.nextLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!check[i][j] && (array[i][j] == 'o' || array[i][j] == 'v')) {
					sheep = wolf = 0;
					dfs(i, j);
					if (sheep > wolf)
						answerSheep += sheep;
					else
						answerWolf += wolf;
				}
			}
		}

		sb.append(answerSheep).append(" ").append(answerWolf);

	}

	public static void dfs(int x, int y) {
		check[x][y] = true;
		if (array[x][y] == 'v')
			wolf++;
		if (array[x][y] == 'o')
			sheep++;

		for (int i = 0; i < 4; i++) {
			int r = x + dx[i];
			int c = y + dy[i];

			if (r < 0 || c < 0 || r >= R || c >= C || check[r][c]|| array[r][c] == '#')	continue;

			check[r][c] = true;
			dfs(r, c);
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