import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, answer, areaToFive;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N][N];
		answer = Integer.MAX_VALUE;

		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				array[i][j] = in.nextInt();
				areaToFive+=array[i][j];
			}
		}

		solve();
	}

	private static void solve() {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 < N; d2++) {
						if(i+d1+d2 >= N) continue;
						if (j - d1 < 0 || j + d2 >= N) continue;
						
						DivisionArea(i, j, d1, d2);
					}
				}
			}
		}
	}

	 static void DivisionArea(int x, int y, int d1, int d2) {
	        boolean[][] border = new boolean[N][N];

	        // 경계선 세팅
	        for (int i = 0; i <= d1; i++) {
	            border[x + i][y - i] = true;
	            border[x + d2 + i][y + d2 - i] = true;
	        }

	        for (int i = 0; i <= d2; i++) {
	            border[x + i][y + i] = true;
	            border[x + d1 + i][y - d1 + i] = true;
	        }

	        int[] sum = new int[5];

	        // 1 구역 인구수
	        for (int i = 0; i < x + d1; i++) {
	            for (int j = 0; j <= y; j++) {
	                if (border[i][j]) break;
	                sum[0] += array[i][j];
	            }
	        }

	        // 2 구역 인구수
	        for (int i = 0; i <= x + d2; i++) {
	            for (int j = N - 1; j > y; j--) {
	                if (border[i][j]) break;
	                sum[1] += array[i][j];
	            }
	        }

	        // 3 구역 인구수
	        for (int i = x + d1; i < N; i++) {
	            for (int j = 0; j < y - d1 + d2; j++) {
	                if (border[i][j]) break;
	                sum[2] += array[i][j];
	            }
	        }

	        // 4 구역 인구수
	        for (int i = x + d2 + 1; i < N; i++) {
	            for (int j = N - 1; j >= y - d1 + d2; j--) {
	                if (border[i][j]) break;
	                sum[3] += array[i][j];
	            }
	        }

	        // 5 구역 인구수
	        sum[4] = areaToFive;

	        for (int i = 0; i < 4; i++) {
	            sum[4] -= sum[i];
	        }

	        Arrays.sort(sum);
	        answer = Math.min(answer, sum[4] - sum[0]);
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