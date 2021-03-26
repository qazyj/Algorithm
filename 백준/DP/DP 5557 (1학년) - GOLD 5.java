import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int N;
	static long[][] dp;
	static int[] array;

    public static void main(String[] args) throws Exception {
        SetData();
        System.out.println(dp[array[N - 1]][N - 2]);
    }
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

        N = in.nextInt();
        
        array = new int[N];
        dp = new long[21][N - 1];
        
        for(int i = 0; i < N; i++) {
            array[i]=  in.nextInt();
        }
        dp[array[0]][0]=1;
        
        for (int i = 1; i < dp[0].length; i++) {
            for (int j = 0; j < 21; j++) {
                long plusCount = 0;
                long minusCount = 0;
                if(j - array[i] >= 0){
                    plusCount = dp[j - array[i]][i - 1];
                }
                if(j + array[i] <= 20){
                    minusCount = dp[j + array[i]][i - 1];
                }
                dp[j][i] = plusCount + minusCount;
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