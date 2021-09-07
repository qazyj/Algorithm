import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int T = in.nextInt();
		sb = new StringBuilder();

		while(--T>=0) {
			char[] s = in.nextLine().toCharArray();
			int end = s.length-1;
			if(IsPalindrome(s, 0, end)) {
				sb.append(0).append("\n");
			} else if(IsSimilarPalindrom(s, 0, end)) {
				sb.append(1).append("\n");
			} else {
				sb.append(2).append("\n");
			}
		}
	}

	// if 회문 0
	// else if 유사회문 1
	// else 2
	private static boolean IsPalindrome(char[] str, int s, int e) {
		while(s<=e) {
			if(str[s] != str[e])
				return false;
			s++;
			e--;
		}
		return true;
	}

	private static boolean IsSimilarPalindrom(char[] str, int s, int e) {
		while(s<=e) {
			if(str[s] != str[e])  {
				if(IsPalindrome(str,s+1, e) || IsPalindrome(str,s,e-1))
					return true;
				return false;
			}
			s++;
			e--;
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