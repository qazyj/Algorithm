import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, p1, p2, answer;
	static int[][] array;
	static int[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		p1 = in.nextInt();
		p2 = in.nextInt();
		array = new int[N+1][N+1];
		int m = in.nextInt();
		for(int i = 0; i < m; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			array[x][y] = 1;
			array[y][x] = 1;
		}

		check = new int[N+1];
		bfs();

		if(check[p2] == 0)
			answer = -1;
		else
			answer = check[p2];
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(p1);

		while(!queue.isEmpty()) {
			int v = queue.poll();
			if(v == p2) break;

			for(int i  = 1; i <= N; i++) {
				if(array[v][i] != 1 || check[i] != 0) continue;

				check[i] = check[v] + 1;
				queue.add(i);
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