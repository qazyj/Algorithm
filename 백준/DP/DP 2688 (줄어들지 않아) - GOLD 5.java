import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int T;
	static long[][] array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// µ•¿Ã≈Õ
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		T = in.nextInt();
		sb = new StringBuilder();
		array = new long[65][10];
		
		array[0][0] = 1;
		array[0][1] = 1;
		array[0][2] = 1;
		array[0][3] = 1;
		array[0][4] = 1;
		array[0][5] = 1;
		array[0][6] = 1;
		array[0][7] = 1;
		array[0][8] = 1;
		array[0][9] = 1;
		
		for(int i = 1 ; i < 65; i++) {
			for(int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					array[i][j] += array[i - 1][k];
				}
			}
		}
		
		for(int t = 0; t < T; t++) {
			int n = in.nextInt();
			
			long sum = 0;
			for(int i = 0 ; i < 10; i++) {
				sum += array[n-1][i];
			}
			
			sb.append(sum).append("\n");
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