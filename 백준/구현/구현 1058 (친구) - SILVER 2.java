import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		boolean[][] friends = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String s = in.nextLine();
			for(int j = 0; j < n; j++) {
				if(s.charAt(j) == 'N') continue;
				friends[i][j] = true;
			}
		}

		int answer = 0;
		for(int i = 0; i < n; i++) {
			Set<Integer> set = new HashSet<>();
			for(int j = 0; j < n; j++) {
				if(!friends[i][j]) continue;
				if(!set.contains(j)) {
					set.add(j);
				}

				for(int z = 0; z < n; z++) {
					if(!friends[j][z]) continue;
					if(i == z) continue;

					if(!set.contains(z)) {
						set.add(z);
					}
				}
			}

			answer = Math.max(answer, set.size());
		}
		System.out.println(answer);
	}
}

class Node {
	long value;
	long count;

	public Node(long value, long count) {
		this.value = value;
		this.count = count;
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