import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for(int i = 0; i < testcase; i++) {
			String s = in.nextLine();
			int a = 0;
			int b = 0;
			int minX = 0;
			int maxX = 0;
			int minY = 0;
			int maxY = 0;

			int direction = 0;
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == 'F') {
					a += dx[direction%4];
					b += dy[direction%4];
					maxX = Math.max(maxX, a);
					minX = Math.min(minX, a);
					maxY = Math.max(maxY, b);
					minY = Math.min(minY, b);
				} else if(s.charAt(j) == 'B') {
					a -= dx[direction%4];
					b -= dy[direction%4];
					maxX = Math.max(maxX, a);
					minX = Math.min(minX, a);
					maxY = Math.max(maxY, b);
					minY = Math.min(minY, b);
				} else if(s.charAt(j) == 'L') {
					direction--;
					if(direction==-1) direction+=4;
				} else if(s.charAt(j) == 'R') {
					direction++;
				}
			}

			sb.append((maxX - minX) *(maxY -minY)).append("\n");
		}
	}
}

class Node {
	int x;
	int y;
	int direction;

	public Node(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
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