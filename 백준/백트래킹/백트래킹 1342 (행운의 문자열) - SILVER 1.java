import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static char[] alphabet;
	static int answer;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		String str = in.nextLine();
		alphabet = new char[27];
		for(int  i = 0; i < str.length(); i++)
			alphabet[str.charAt(i) - 'a']++;
		answer = 0;
		dfs(0, "", str.length());

		System.out.println(answer);
	}

	public static void dfs(int index, String temp, int len) {
		if(index == len) {
			answer++;
			return;
		}

		for(int i = 0; i < 26; i++) {
			if(alphabet[i] == 0) continue;
			if(temp != "" && temp.charAt(temp.length() - 1) == (char)('a' + i)) continue;

			alphabet[i]--;
			dfs(index + 1, temp + (char)('a' + i), len);
			alphabet[i]++;
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