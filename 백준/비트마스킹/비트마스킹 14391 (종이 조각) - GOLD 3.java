import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		int m = in.nextInt();
		int[][] arr = new int[n][m];

		for(int i = 0; i < n; i++) {
			String s = in.nextLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}

		int answer = 0;
		for(int bit = 0; bit < (1 << (n*m)); bit++) {
			int sum = 0;
			// 가로   0 check
			for(int i = 0; i < n; i++) {
				int cur = 0;
				for(int j = 0; j < m; j++) {
					int k = i*m+j;  // 현재 i,j 위치가 0이면 가로 더해주기
					if((bit&(1<<k)) == 0) {
						cur*=10;
						cur += arr[i][j];
					} else {
						sum += cur;
						cur = 0;
					}
				}
				sum += cur;
			}
			// 세로       1 check
			for(int j = 0; j < m; j++) {
				int cur = 0;
				for(int i = 0; i < n; i++) {
					int k = i*m+j;      // 현재 i,j
					if((bit&(1<<k)) == (1<<k)) {
						cur *= 10;
						cur += arr[i][j];
					} else {
						sum += cur;
						cur = 0;
					}
				}
				sum += cur;
			}
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
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