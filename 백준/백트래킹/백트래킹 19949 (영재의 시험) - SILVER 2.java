import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static int[] arr;
	static long answer;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		arr = new int[10];
		for(int i = 0; i < 10; i++) arr[i] = in.nextInt();
		answer = 0;
		dfs(0, 0, 0, 0);
		System.out.println(answer);
	}

	public static void dfs(int count, int pre, int cantUse, int correct) {
		if(count == 10) {
			if(correct >= 5) answer++;
			return;
		}

		for(int i = 1; i <= 5; i++) {
			if(cantUse == i) continue;

			if(arr[count] == i) {
				if(pre == i) {
					dfs(count+1, 0, i, correct+1);
				} else {
					dfs(count+1, i, 0, correct+1);
				}
			} else {
				if(pre == i) {
					dfs(count+1, 0, i, correct);
				} else {
					dfs(count+1, i, 0, correct);
				}
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