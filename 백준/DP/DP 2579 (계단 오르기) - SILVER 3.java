import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int n;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(dp[n]);
	}
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		n = in.nextInt();
		int[] steps = new int[n+1];
		dp = new int[n+1];
		int noJump = 0, jump = 0;
		
		for(int i = 1; i <= n; i++)
			steps[i] = in.nextInt();
		
		dp[1] = steps[1];
		if(n>=2)
			dp[2] = steps[1] + steps[2];
		
		for(int i = 3; i <= n; i++) {
			noJump = steps[i] + steps[i-1] + dp[i-3];
			jump = steps[i] + dp[i-2];
					
			dp[i] = Math.max(noJump, jump);
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