import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int count = 0;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		m = in.nextInt();
		boolean zero = true;
		List<Integer> answer = new ArrayList<>();

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = in.nextInt();
				if(arr[i][j] == 1) {
					zero = false;
				}
			}
		}

		if(zero == true) {
			System.out.println("0");
			System.out.println("0");
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				count = 0; //////////////////// 1
				if (!visited[i][j] && arr[i][j] == 1) {
					dfs(i, j);
					answer.add(count);
				}
			}
		}
		Collections.sort(answer);
		System.out.println(answer.size());
		System.out.println(answer.get(answer.size() - 1));
	}

	static void dfs(int x, int y) {
		visited[x][y] = true;
		count++;

		for(int d = 0; d < 4; d++) {
			int r = x + dx[d];
			int c = y + dy[d];

			if(r < 0 || c < 0 || r >= n || c >= m) continue;
			if(visited[r][c] || arr[r][c] != 1) continue;
			dfs(r,c);

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