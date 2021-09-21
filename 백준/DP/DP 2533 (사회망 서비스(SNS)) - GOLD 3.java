import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static ArrayList<Integer>[] array;
	static int[][] dp;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData()throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new ArrayList[N+1];
		check = new boolean[N+1];
		dp = new int[N+1][2];

		for(int i = 1; i <= N; i++)
			array[i] = new ArrayList<>();

		for(int i = 1; i < N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			array[a].add(b);
			array[b].add(a);
		}

		dfs(1);
		answer = Math.min(dp[1][0], dp[1][1]);
	}

	private static void dfs(int index) {
		check[index] = true;
		dp[index][0] = 0;
		dp[index][1] = 1;

		for(int i = 0 ; i < array[index].size(); i++) {
			int nextIndex = array[index].get(i);
			if(check[nextIndex]) continue;

			dfs(nextIndex);
			dp[index][0] += dp[nextIndex][1];
			dp[index][1] += Math.min(dp[nextIndex][0], dp[nextIndex][1]);
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