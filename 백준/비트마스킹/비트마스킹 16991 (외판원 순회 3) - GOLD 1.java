import java.io.*;
import java.util.*;

public class Main {
	static double[][] arr;
	static double[][] dp;
	static Node[] nodes;
	static int n;
	static final int INF = 11000000;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		arr = new double[n][n];
		nodes = new Node[n];
		for(int i = 0; i < n; i++) {
			nodes[i] = new Node(in.nextInt(), in.nextInt());
		}

		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				double dif = Math.sqrt(Math.pow(Math.abs(nodes[i].x - nodes[j].x),2) +
						Math.pow(Math.abs(nodes[i].y - nodes[j].y),2));

				arr[i][j] = dif;
				arr[j][i] = dif;
			}
		}

		dp = new double[(1<<n)-1][n];
		for(int i = 0; i < (1<<n)-1; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(recursive(0, 1));
	}

	public static double recursive(int current, int bit) {
		if(bit == (1<<n)-1) {
			return arr[current][0];
		}

		if(dp[bit][current] != -1){
			return dp[bit][current];
		}
		dp[bit][current] = INF;

		for(int i = 0; i < n; i++) {
			if((bit & (1<<i)) != 0 || arr[current][i] == 0) continue;

			dp[bit][current] = Math.min(dp[bit][current], recursive(i, (bit | (1<<i))) + arr[current][i]);
		}
		return dp[bit][current];
	}
}

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
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