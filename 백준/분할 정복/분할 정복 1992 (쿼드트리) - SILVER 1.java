import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int N, r, c, answer;
	static int[] x = {0, 0, 1, 1};
	static int[] y = {0, 1, 0, 1};

	public static void main(String[] args) throws Exception {
		SetData();
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		r = in.nextInt();
		c = in.nextInt();
		answer = 0;
		
        dfs((int)Math.pow(2, N), 0, 0);
	}

    private static void dfs(int n, int i, int j) {
        if(n == 2) {
            for (int direction = 0; direction < 4; direction++) {
                int rx = i + x[direction];
                int ry = j + y[direction];
            	
                if (rx == r && ry == c) {
                    System.out.println(answer);
                    System.exit(0);
                }
                answer++;
            }
            return;
        }
        
		for (int s = i; s < i+n; s+=n/2) {
			for (int e = j; e < j+n; e+=n/2) {
				if(s+n/2-1<r || e+n/2-1<c) {
					answer+=(n/2)*(n/2);
					continue;
				}
				dfs(n/2,s,e);
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