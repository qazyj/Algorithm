import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int R, C, answer, alphas[][];
	static char[][] array;
	static Queue<int[]> queue;
	static boolean[] check;
	static int[] x = { 0, 1, 0, -1 };
	static int[] y = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		SetData();
		dfs();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		R = in.nextInt();
		C = in.nextInt();
		check = new boolean[26];
		array = new char[R][C];
		alphas = new int[R][C];
		answer = 0;

		for (int i = 0; i < R; i++)
			array[i] = in.nextLine().toCharArray();

		queue = new LinkedList<>();

		queue.add(new int[] { 0, 0, 1 << (array[0][0] - 'a'), 1 });
	}

	public static void dfs() {
		while (!queue.isEmpty()) {

			int[] location = queue.poll();
			if (alphas[location[0]][location[1]] == location[2])	continue;
			alphas[location[0]][location[1]] = location[2];
			answer = location[3];

			for (int i = 0; i < 4; i++) {
				int r = location[0] + x[i];
				int c = location[1] + y[i];

				if (r < 0 || c < 0 || r >= R || c >= C)		continue;

				int next = 1 << (array[r][c] - 'a');

				if ((location[2] & next) == 0) {
					queue.add(new int[] { r, c, location[2] | next, location[3] + 1 });
				}

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