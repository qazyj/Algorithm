import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int M, N;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static int[][] array;
	static boolean[][] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		sb = new StringBuilder();
		int testCase = in.nextInt();

		for (int i = 0; i < testCase; i++) {
			M = in.nextInt();
			N = in.nextInt();
			int K = in.nextInt();
			array = new int[M][N];
			check = new boolean[M][N];
			
			for (int j = 0; j < K; j++) {
				array[in.nextInt()][in.nextInt()] = 1;
			}

			int count = 0;
			for (int a = 0; a < M; a++) {
				for (int b = 0; b < N; b++) {
					if (!check[a][b] && array[a][b] == 1) {
						count++;
						check[a][b] = true;
						bfs(a, b);
					}
				}
			}
			
			sb.append(count + "\n");
		}
	}
	
	private static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { a, b });

		while (!queue.isEmpty()) {
			int location[] = queue.poll();

			for (int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r >= 0 && c >= 0 && r < M && c < N) {
					if (array[r][c] == 1 && !check[r][c]) {
						queue.offer(new int[] { r, c });
						check[r][c] = true;
					}
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