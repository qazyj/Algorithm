import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int C, P, answer;
	static int[] array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		C = in.nextInt();
		P = in.nextInt();
		array = new int[C];
		answer = 0;
		for(int i = 0; i < C; i++)
			array[i] = in.nextInt();

		switch(P) {
			case 1:
				answer = C + solve(new int[]{0,0,0,0});
				break;
			case 2:
				answer = solve(new int[]{0,0});
				break;
			case 3:
				answer = solve(new int[]{1,1,0})
						+ solve(new int[]{0,1});
				break;
			case 4:
				answer = solve(new int[]{0,1,1})
						+ solve(new int[]{1,0});
				break;
			case 5:
				answer = solve(new int[]{0,0,0})
						+ solve(new int[]{1,0})
						+ solve(new int[]{0,1,0})
						+ solve(new int[]{0,1});
				break;
			case 6:
				answer = solve(new int[]{0,0,0})
						+ solve(new int[]{0,0})
						+ solve(new int[]{1,0,0})
						+ solve(new int[]{0,2});
				break;
			case 7:
				answer = solve(new int[]{0,0,0})
						+ solve(new int[]{2,0})
						+ solve(new int[]{0,0,1})
						+ solve(new int[]{0,0});
				break;
		}
	}

	static int solve(int[] shape) {
		int result = 0;
		int size = shape.length;

		for (int i = 0; i <= C-size; i++) {
			int gap = shape[0] + array[i];

			// 빈공간 check
			boolean ok = true;
			for (int j = 0; j < size; j++) {
				if(shape[j] + array[i+j] != gap) {
					ok = false;
					break;
				}
			}
			if(ok) result++;
		}
		return result;
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