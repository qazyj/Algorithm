import java.io.*;
import java.util.*;

public class Main {
	static int answer, N, Z, M;
	static Set<Integer> set;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);
		N = in.nextInt();
		Z = in.nextInt();
		M = in.nextInt();
		set = new HashSet<>();
		check = new boolean[N + 1];

		for (int i = 0; i < M; i++)
			check[in.nextInt()] = true;

		answer = dfs();
	}

	private static int dfs() {
		for (int i = 1; i < 1000; i++) {
			int value = 1;
			boolean visit[] = check.clone();
			while (value < 1000) {
				if (value == Z) {
					return i;
				}

				if(visit[value]) break;
				else visit[value] = true;

				value = (value+i)%N;
			}
		}
		return -1;
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