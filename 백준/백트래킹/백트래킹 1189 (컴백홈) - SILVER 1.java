import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static int r,c,k, answer;
	static char[][] arr;
	static int[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		r = in.nextInt();
		c = in.nextInt();
		k = in.nextInt();
		arr = new char[r][c];
		visited = new int[r][c];

		for(int i=0; i<r; i++) {
			String s = in.nextLine();
			for(int j=0; j<c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		visited[r-1][0] = 1;
		answer = 0;
		dfs(r-1, 0, 1);

		System.out.println(answer);
	}

	static void dfs(int x, int y, int moved) {
		if(x == 0 && y == c-1) {
			if(moved == k)
				answer++;
			return;
		}

		for(int d=0; d<4; d++) {
			int nextR = x + dx[d];
			int nextC = y + dy[d];

			if(nextR<0 || nextR>=r || nextC<0 ||nextC>=c) continue;
			if(visited[nextR][nextC] == 1 || arr[nextR][nextC] == 'T') continue;

			visited[nextR][nextC] = 1;
			dfs(nextR, nextC, moved+1);
			visited[nextR][nextC] = 0;

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