import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static boolean[] prime, flag, number;
	static StringBuilder sb;
	static int N;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		prime = new boolean[1000001];
		flag = new boolean[1000001];
		number = new boolean[1000001];
		sb = new StringBuilder();
		N = in.nextInt();

		getPrimeNumber();
		getSangGeunSoo(N);

		for (int i = 1; i <= N; i++) {
			if (getSangGeunSoo(i) && !prime[i]) {
				sb.append(i).append("\n");
			}
		}
	}

	public static boolean getSangGeunSoo(int index) {
		// basecase
		if (index == 1)	return true;
		
		if (flag[index])  return number[index];
		
		flag[index] = true;

		int sum = 0, div = 1000001, cur = index;

		while (cur > 0) {
			int temp = cur / div;
			sum += Math.pow(temp, 2);
			cur %= div;
			div /= 10;
		}
		return number[index] = getSangGeunSoo(sum);

	}

	private static void getPrimeNumber() {
		prime[0] = prime[1] = true;
		for (int i = 2; i <= 1000000; i++) {
			// true면 이미 소수이므로 pass
			if (prime[i]) {
				continue;
			}

			// 해당 수로 나누어 떨어지는 수는 소수이므로 true로 check
			for (int j = i + i; j <= 1000000; j += i) {
				prime[j] = true;
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