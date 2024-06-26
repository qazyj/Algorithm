import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static int a,b;
	static int[] arr;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		answer = -1;
		a = in.nextInt();
		b = in.nextInt();
		String s = String.valueOf(a);
		arr = new int[s.length()];
		visited = new boolean[s.length()];

		for(int i = 0; i < s.length(); i++) {
			arr[i] = s.charAt(i)-'0';
		}
		dfs(0,0,arr.length);
		System.out.println(answer);
	}

	public static void dfs(int value, int depth, int r) {
		if(depth == r) {
			answer = Math.max(answer, value);
			return;
		}

		for(int i = 0; i < arr.length; i++) {
			if(visited[i] || (depth ==0 && arr[i] == 0)) continue;
			if(value*10 + arr[i] > b) continue;

			visited[i] = true;
			dfs(value*10 + arr[i], depth+1, r);
			visited[i] = false;
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