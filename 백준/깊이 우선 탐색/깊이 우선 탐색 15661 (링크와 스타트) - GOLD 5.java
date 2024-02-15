import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static int n, answer;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		arr = new int[n][n];
		visited = new boolean[n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = in.nextInt();
			}
		}

		answer = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(answer);
	}

	public static void dfs(int index) {
		if(index == n) {
			int start = 0;
			int link = 0;

			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					if(visited[i] != visited[j]) continue;

					if(visited[i]) {
						start += (arr[i][j] + arr[j][i]);
					} else {
						link += (arr[i][j] + arr[j][i]);
					}
				}
			}

			answer = Math.min(answer, Math.abs(start-link));
			return;
		}

		visited[index] = true;
		dfs(index+1);
		visited[index] = false;
		dfs(index+1);
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