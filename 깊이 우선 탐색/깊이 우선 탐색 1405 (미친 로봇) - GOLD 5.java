import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int N;
	static double percentage;	
	static double[] percent;	// 각 방향으로 이동할 확률
	static boolean[][] check;	// 단순 경로인지 아닌지 체크
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(14, 14, 0, 1.0);
		System.out.println(percentage);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		percent = new double[4];
		check = new boolean[30][30];
		percentage = 0.0;
		check[14][14] = true;	

		N = in.readInt();		

		// 입력값을 확률로 바꿈
		for (int i = 0; i < 4; i++) 
			percent[i] = in.readInt() * 0.01;
		
	}

	private static void dfs(int a, int b, int count, double per) {
		// basecase
		if (count == N) {
			percentage += per;
			return;
		}

		// 4방향 이동
		for (int i = 0; i < 4; i++) {
			int r = a + x[i];
			int c = b + y[i];
			if (!check[r][c]) {
				check[r][c] = true;
				dfs(r, c, count + 1, per * percent[i]);
				check[r][c] = false;
			}
		}
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
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
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
