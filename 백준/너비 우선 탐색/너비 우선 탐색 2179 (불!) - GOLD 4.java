import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int N, M;
	static char[][] array;
	static Queue<int[]> jihoon, fire;
	static int[] x = { -1, 0, 1, 0 };
	static int[] y = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println("IMPOSSIBLE");
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		array = new char[N][M];
		jihoon = new LinkedList<>();
		fire = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String s = in.nextLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = s.charAt(j);
				if (array[i][j] == 'F')
					fire.add(new int[] { i, j });
				if (array[i][j] == 'J')
					jihoon.add(new int[] { i, j, 0 });
			}
		}

		bfs();
	}

	private static void bfs() {
		while (!jihoon.isEmpty()) {
			// 불이 먼저 퍼진다.
			int size = fire.size();
			for (int i = 0; i < size; i++) {
				int[] fireLocation = fire.poll();

				for (int direction = 0; direction < 4; direction++) {
					int r = fireLocation[0] + x[direction];
					int c = fireLocation[1] + y[direction];

					if (r < 0 || c < 0 || r >= N || c >= M)
						continue;
					if (array[r][c] == '#' || array[r][c] == 'F')
						continue;

					array[r][c] = 'F';
					fire.add(new int[] { r, c });
				}
			}

			size = jihoon.size();
			for (int i = 0; i < size; i++) {
				int[] jihoonLocation = jihoon.poll();
				for (int direction = 0; direction < 4; direction++) {
					int r = jihoonLocation[0] + x[direction];
					int c = jihoonLocation[1] + y[direction];

					// 나가는 경우
					if (r < 0 || c < 0 || r >= N || c >= M) {
						System.out.println(jihoonLocation[2] + 1);
						System.exit(0);
					}

					if (array[r][c] == '#' || array[r][c] == 'F' || array[r][c] == 'J')
						continue;

					array[r][c] = 'J';
					jihoon.add(new int[] { r, c, jihoonLocation[2] + 1 });
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