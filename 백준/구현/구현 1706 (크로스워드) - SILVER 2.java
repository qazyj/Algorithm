import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static String answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int R = in.nextInt();
		int C = in.nextInt();
		char[][] array = new char[R][C];
		PriorityQueue<String> pq = new PriorityQueue<>();
		for(int i = 0; i < R; i++) {
			String s = in.nextLine();
			for(int j = 0; j < C ; j++)
				array[i][j] = s.charAt(j);
		}

		for(int i = 0; i < R; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < C; j++) {
				if(array[i][j] == '#') {
					if(sb.length() > 1) {
						pq.add(sb.toString());
					}
					sb.setLength(0);
				} else {
					sb.append(array[i][j]);
					if(j == C-1) {
						if(sb.length() > 1) {
							pq.add(sb.toString());
						}
					}
				}
			}
		}

		for(int i = 0; i < C; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < R; j++) {
				if(array[j][i] == '#') {
					if(sb.length() > 1) {
						pq.add(sb.toString());
					}
					sb.setLength(0);
				} else {
					sb.append(array[j][i]);
					if(j == R-1) {
						if(sb.length() > 1) {
							pq.add(sb.toString());
						}
					}
				}
			}
		}

		answer = pq.poll();
	}
}

class Node {
	int x;
	int y;
	int count;

	public Node(int x, int y, int count) {
		this.x = x;
		this.y = y;
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