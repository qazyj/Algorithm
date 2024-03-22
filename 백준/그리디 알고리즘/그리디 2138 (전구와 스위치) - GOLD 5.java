import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		for(int i = 0; i < n; i++) {
			a[i] = s1.charAt(i)-'0';
			b[i] = s2.charAt(i)-'0';
		}

		int[] c = Arrays.copyOf(a, n);
		c[0] = 1-c[0];
		c[1] = 1-c[1];

		int answer = getAnswer(n, a, b);
		int answer2 = getAnswer(n, c, b);
		if (answer2 != -1) answer2++;

		if (answer == -1)
			System.out.println(answer2);
		else if (answer2 == -1)
			System.out.println(answer);
		else
			System.out.println(Math.min(answer2, answer));

	}

	public static int getAnswer(int n, int[] a, int[] b) {
		int count = 0;
		for (int i = 0; i < n-1; i++) {
			if(a[i] == b[i]) continue;

			count++;
			a[i] = 1-a[i];
			a[i+1] = 1-a[i+1];
			if (i != n-2)
				a[i+2] = 1-a[i+2];
		}

		return a[n-1]!=b[n-1] ? -1 : count;
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