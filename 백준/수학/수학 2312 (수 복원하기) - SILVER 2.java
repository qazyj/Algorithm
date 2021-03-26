import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		int N = in.nextInt();

		int[] primes = {
				2 ,   3,    5 ,   7  ,  11 ,   13 ,   17 ,   19 ,   23  ,  29,
				31 ,   37  ,  41   , 43 ,   47 ,   53 ,  59   ,61   , 67  ,  71,
				73  ,  79  ,  83  ,  89  ,  97  ,  101 ,   103   , 107 ,   109  ,  113,
				127  ,  131  ,  137   , 139   , 149 ,   151 ,   157 ,   163,    167 ,   173,
				179  ,  181   , 191  ,  193  ,  197  ,  199  ,  211 ,   223 ,   227   , 229,
				233   , 239 ,   241   , 251 ,   257 ,   263 ,   269  ,  271  ,  277   , 281,
				283    ,293  ,  307  ,  311  ,  313  ,  317   
		};
		int[] count = new int[66];
		
		for (int i = 0; i < N; i++) {
			int number = in.nextInt();
			for (int j = 0; j < 66; j++) {
				while (number % primes[j] == 0) {
					number /= primes[j];
					count[j]++;
				}
			}
			for (int c = 0; c < 66; c++) {
				if (count[c] != 0) {
					sb.append(Integer.toString(primes[c]) + " " + Integer.toString(count[c]) + "\n");
					count[c] = 0;
				}
			}
			if (number != 1) sb.append(Integer.toString(number) + " 1\n");
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