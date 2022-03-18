import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int n;
	static int[][] array, copy;

	public static void main(String[] args) throws Exception {
		SetData();

		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for(int i = 0; i < testcase; i++) {
			n = in.nextInt();
			int spin = in.nextInt()+360;
			array = new int[n][n];
			copy = new int[n][n];
			spin/=45;
			for(int a = 0; a < n; a++) {
				for(int b = 0; b < n; b++) {
					array[a][b] = in.nextInt();
					copy[a][b] = array[a][b];
				}
			}

			while(spin-->0) {
				Spin();
			}

			for(int a = 0; a < n; a++) {
				for(int b = 0; b < n; b++) {
					sb.append(array[a][b] + " ");
				}
				sb.append("\n");
			}
		}
	}

	public static void Spin() {
		for(int i = 0; i < n ; i++){
			copy[i][n/2] = array[i][i];
			copy[i][i] = array[n/2][i];
			copy[n/2][i] = array[n-i-1][i];
			copy[n-i-1][i] = array[n-i-1][n/2];
		}

		for(int i = 0; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				array[i][j] = copy[i][j];
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