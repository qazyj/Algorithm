import java.io.*;
import java.util.*;

public class Main {
	static int k,s, count;
	static int[] arr;
	static boolean[] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		count = 0;
		while(true) {
			k = in.nextInt();
			if(k==0) break;
			arr = new int[k];
			check = new boolean[k];

			for(int i = 0; i < k; i++) arr[i] = in.nextInt();

			backtracking(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static void backtracking(int depth, int cur) {
		if(depth == 6) {
			for(int i = 0; i < k; i++) {
				if(check[i]) sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for(int i = cur; i < k; i++) {
			check[i] = true;
			backtracking(depth+1, i+1);
			check[i]= false;
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