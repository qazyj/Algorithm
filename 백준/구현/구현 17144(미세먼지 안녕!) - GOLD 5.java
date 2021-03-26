import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Algorithm {
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static int[][] array, temp;
	static int r, c, answer, airCleaner1, airCleaner2;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		answer = 2;
		r = in.nextInt();
		c = in.nextInt();
		int testcase = in.nextInt();
		array = new int[r][c];
		temp = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				array[i][j] = in.nextInt();
				answer += array[i][j];
				if (array[i][j] < 0) {
					if (airCleaner1 == 0) {
						airCleaner1 = i;
					} else {
						airCleaner2 = i;
					}
				}

			}
		}

		while (testcase-- > 0) {
			SpreadFineDust();
			PlayAirCleaner();
			CopyMap(temp, array);
		}
	}

	private static void SpreadFineDust() {
		for(int i = 0; i < r; i++)
			Arrays.fill(temp[i], 0);
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				temp[i][j] += array[i][j];

				if(temp[i][j] == -1) continue;
				if(array[i][j] == 0) continue;
				
				for (int direction = 0; direction < 4; direction++) {
					int R = i + x[direction];
					int C = j + y[direction];

					if (R < 0 || C < 0 || R >= r || C >= c)	continue;
					if (array[R][C] == -1)	continue;

					temp[R][C] += (array[i][j] / 5);
					temp[i][j] -= (array[i][j] / 5);
				}
			}
		}
	}

	private static void PlayAirCleaner() {
		// 위쪽 공기청정기는 반시계방향
		int top = airCleaner1;

		answer -= temp[top-1][0];
		for (int x = top - 1; x > 0; x--) {
			temp[x][0] = temp[x - 1][0];
		}

		for (int y = 0; y < c - 1; y++) {
			temp[0][y] = temp[0][y + 1];
		}

		for (int x = 0; x < top; x++) {
			temp[x][c - 1] = temp[x + 1][c - 1];
		}

		for (int y = c - 1; y > 1; y--) {
			temp[top][y] = temp[top][y - 1];
		}

		temp[top][1] = 0;

		// 아래쪽 공기청정기는 시계 방향
		int bottom = airCleaner2;

		answer -= temp[bottom + 1][0];
		for (int x = bottom + 1; x < r - 1; x++) {
			temp[x][0] = temp[x + 1][0];
		}

		for (int y = 0; y < c - 1; y++) {
			temp[r - 1][y] = temp[r - 1][y + 1];
		}

		for (int x = r - 1; x > bottom; x--) {
			temp[x][c - 1] = temp[x - 1][c - 1];
		}

		for (int y = c - 1; y > 1; y--) {
			temp[bottom][y] = temp[bottom][y - 1];
		}

		temp[bottom][1] = 0;
	}
	
	static void CopyMap(int[][] one,int[][] two) {
		for(int i=0;i<one.length;++i) {
			System.arraycopy(one[i], 0, two[i], 0, two[i].length);
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