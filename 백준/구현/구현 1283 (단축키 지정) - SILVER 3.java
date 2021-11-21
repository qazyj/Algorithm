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

		sb = new StringBuilder();
		int N = in.nextInt();
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			String[] s = in.nextLine().split(" ");
			boolean check = false;
			for (int j = 0; j < s.length; j++) {
				if (!set.contains(Character.toUpperCase(s[j].charAt(0)))) {
					check = true;
					set.add(Character.toUpperCase(s[j].charAt(0)));
					s[j] = ReplaceString(s[j], 0);
					break;
				}
			}

			if (!check) {
				for (int j = 0; j < s.length; j++) {
					boolean check2 = false;
					for (int k = 0; k < s[j].length(); k++) {
						if (!set.contains(Character.toUpperCase(s[j].charAt(k)))) {
							check2 = true;
							set.add(Character.toUpperCase(s[j].charAt(k)));
							s[j] = ReplaceString(s[j], k);
							break;
						}
					}
					if(check2) break;
				}
			}

			for (int j = 0; j < s.length; j++) {
				sb.append(s[j] + " ");
			}
			sb.append("\n");
		}
	}

	private static String ReplaceString(String s, int index) {
		s = s.substring(0, index) + "[" + s.charAt(index) + "]" + s.substring(index + 1, s.length());
		return s;
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