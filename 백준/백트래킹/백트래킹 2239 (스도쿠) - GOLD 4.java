import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static int[][] array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		array = new int[9][9];
		sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			String s = in.nextLine();
			for (int j = 0; j < 9; j++)
				array[i][j] = s.charAt(j) - '0';
		}
		
		dfs(0, 0);
	}
	
	static void dfs(int r, int c) {

		if (c == 9) {
			dfs(r + 1, 0);
			return;
		}

		if (r == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(array[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}

		if (array[r][c] != 0) { 
			dfs(r, c+ 1);
			return;
		}
			
		for (int i = 1; i <= 9; i++) {
			if (Sudoku(r, c, i)) {
				array[r][c] = i;
				dfs(r, c + 1);
			}
			array[r][c] = 0;
		}		
	}

	static boolean Sudoku(int r, int c, int value) {
		// За
		for (int i = 0; i < 9; i++) {
			if (array[r][i] == value) {
				return false;
			}
		}

		// ї­
		for (int i = 0; i < 9; i++) {
			if (array[i][c] == value) {
				return false;
			}
		}

		// 3*3
		int tempR = (r / 3) * 3;
		int tempC = (c / 3) * 3;

		for (int i = tempR; i < tempR + 3; i++) {
			for (int j = tempC; j < tempC + 3; j++) {
				if (array[i][j] == value) {
					return false;
				}
			}
		}

		return true;
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