import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
	static String S, T;

	public static void main(String[] args) throws Exception {
		SetData();
		// System.out.println(answer);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);
		S = in.nextLine();
		T = in.nextLine();

		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < T.length(); i++) {
			list.add(T.charAt(i));
		}

		int index = list.size() - 1;
		while (list.size() > S.length()) {
			if (list.get(index) == 'A') {
				list.remove(index);
			} else {
				list.remove(index);
				list = reverse(list);
			}
			index--;
		}
		
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) != list.get(i).charValue()) {
				System.out.println(0);
				System.exit(0);
			}
		}
		
		System.out.println(1);
	}
	
	static List<Character> reverse(List<Character> list) {
		List<Character> result = new ArrayList<Character>();
		int index = list.size() - 1;
		while (index >= 0) {
			result.add(list.get(index--));
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