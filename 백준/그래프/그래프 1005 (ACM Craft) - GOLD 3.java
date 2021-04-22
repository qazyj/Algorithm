import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Algorithm {
	static ArrayList<Integer>[] need;
	static int[] d;
	static int[] memo;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		while(T --> 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			d = new int[n];
			need = new ArrayList[n];
			memo = new int[n];
			Arrays.fill(memo, -1);
			for(int i=0; i<n; ++i)
				need[i] = new ArrayList<>();
			for(int i=0; i<n; ++i)
				d[i] = in.nextInt();
			for(int i=0; i<k; ++i) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				need[v].add(u);
			}
			int w = in.nextInt() - 1;
			sb.append(time(w)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int time(int x) {
		if(memo[x] != -1)
			return memo[x];
		int ret = 0;
		for(int u : need[x]) {
			ret = Math.max(ret, time(u));
		}
		ret += d[x];
		return memo[x] = ret;
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