import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static ArrayList<Integer>[] array;
	static int[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		sb = new StringBuilder();
		array = new ArrayList[N+1];
		check = new int[N+1];

		for(int i = 1; i <= N; i++) {
			array[i] = new ArrayList<Integer>();
		}

		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			array[a].add(b);
		}

		for (int i = 1; i <= N; i++) {
			dfs(i, new boolean[N + 1]);
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, check[i]);
		}

		for (int i = 1; i <= N; i++) {
			if (check[i] == max) {
				sb.append(i + " ");
			}
		}
	}

	private static void dfs(int index, boolean[] visit) {
		visit[index] = true;

		for (int node : array[index]) {
			if (!visit[node]) {
				check[node]++;
				dfs(node, visit);
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