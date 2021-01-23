import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	private static int N, M;
	private static int[][] array;
	private static int[][] melt;
	private static boolean[][] check;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	
    public static void main(String[] args) throws Exception {
        SetData();
    }
    
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.readInt();
		M = in.readInt();

		array = new int[N][M];
		melt = new int[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.readInt();
			}
		}

		int year = 0;
		while (true) {
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (array[r][c] != 0 && !check[r][c]) {
						dfs(r, c);
						cnt++;
					}
				}
			}

			if (cnt == 0) {
				System.out.println(0);
				return;
			} else if (cnt >= 2) {
				System.out.println(year);
				return;
			}

			melting();
			year++;
		}
	}
	
	private static void dfs(int r, int c) {
		check[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (array[nr][nc] == 0)
					melt[r][c]++;
				if (!check[nr][nc] && array[nr][nc] != 0)
					dfs(nr, nc);
			}
		}
	}

	private static void melting() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (array[i][j] != 0) {
					array[i][j] = (array[i][j] - melt[i][j] < 0) ? 0 : array[i][j] - melt[i][j];
					check[i][j] = false;
					melt[i][j] = 0;
				}
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
