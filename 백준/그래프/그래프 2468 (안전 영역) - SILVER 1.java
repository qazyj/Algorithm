import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int answer, N;
	static int[][] array;
	static boolean[][] under, check;
	static int[] x = {0,0,-1,1};
	static int[] y = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N][N];
		under = new boolean[N][N];
		answer = 1;

		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
			}
		}


		for(int i = 1; i < 101; i++) {
			int count = 0;

			check = new boolean[N][N];
			for(int a = 0; a < N; a++) {
				for(int b = 0; b < N; b++) {
					if(array[a][b] > i) continue;

					under[a][b] = true;
				}
			}

			for(int a = 0; a < N; a++) {
				for(int b = 0; b < N; b++) {
					if(under[a][b] || check[a][b]) continue;

					bfs(a,b);
					count++;
				}
			}

			answer = Math.max(answer, count);
		}
	}

	private static void bfs(int a, int b) {
		check[a][b] = true;

		for(int direction = 0; direction < 4; direction++) {
			int r = a + x[direction];
			int c = b + y[direction];

			if(r < 0 || r >= N || c < 0 || c >= N) continue;
			if(check[r][c] || under[a][b]) continue;

			bfs(r,c);
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