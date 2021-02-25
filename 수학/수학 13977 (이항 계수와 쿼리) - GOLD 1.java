import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;


/* [페르마의 소정리] _ 혜민 코드 참고
a^(p-1) % p = 1
(a/b) % M = ((a % M) * (b^-1 % M)) = ((a % M) * (b^(M-2) % M))
*/

public class Main {
	private static  StringBuilder sb;
    private static int MOD;
    private static long[] F; // 8MB

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		sb = new StringBuilder();
		F = new long[4000001]; // 8MB
		MOD = 1000000007;

        setFactorial();

        int T = in.nextInt();
        while (T-- > 0) {
            int N = in.nextInt();
            int K = in.nextInt();
            sb.append(Combination(N, K)).append('\n');
        }
	}

    private static long Combination(int N, int K) {
        long numer = F[N];
        long denom = (F[N - K] * F[K]) % MOD;
        denom = Pow(denom, MOD - 2);
        
        return (numer * denom) % MOD;
    }

    private static long Pow(long n, int k) {
        long base = 1;
        while (k > 0) {
            if (k % 2 == 1) { base *= n; base %= MOD; }
            n *= n; n %= MOD;
            k /= 2;
        }

        return base;
    }

    private static void setFactorial() {
        F[0] = 1; F[1] = 1;
        for (int i = 2; i < 4000001; i++) {
            F[i] = i * F[i - 1];
            F[i] %= MOD;
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