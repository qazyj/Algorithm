import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
    static final int[][] xy = {{0, 1}, {0, -1}, {1, 0}, {1, 1}, {1, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
    static boolean[][] check;
    static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.print(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		sb = new StringBuilder();
		
        while(true) {
            int x = in.nextInt();
            int y = in.nextInt();

            if(x == 0 && y == 0) {
                return;
            }

            check = new boolean[y + 2][x + 2];

            for(int i = 1; i <= y; i++) {
                for(int j = 1; j <= x; j++) {
                    check[i][j] = in.nextInt() > 0;
                }
            }
            
            int result = 0;
            for(int i = 1; i <= y; i++) {
                for(int j = 1; j <= x; j++) {
                    if(check[i][j]) {
                        result++;
                        dfs(i, j, check);
                    }
                }
            }
            sb.append(result + "\n");
        }
	}

    private static void dfs(int x, int y, boolean[][] islands) {
        for(int[] XY : xy) {
            int r = x + XY[0];
            int c = y + XY[1];

            if(islands[r][c]) {
                islands[r][c] = false;
                dfs(r, c, islands);
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