import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int answer;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		array = new int[22][22];
		answer = 1;
		int length;
		for (int i = 0; i < N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			if (i % 2 == 0) {
				array[a][b] = 1;
			} else {
				array[a][b] = 2;
			}

			length = Search(a, b, -1, 0, array[a][b]) + Search(a, b, 1, 0, array[a][b]) + 1;
			if (length == 5) break;

			length = Search(a, b, 0, -1, array[a][b]) + Search(a, b, 0, 1, array[a][b]) + 1;
			if (length == 5) break;

			length = Search(a, b, -1, -1, array[a][b]) + Search(a, b, 1, 1, array[a][b]) + 1;
			if (length == 5) break;

			length = Search(a, b, -1, 1, array[a][b]) + Search(a, b, 1, -1, array[a][b]) + 1;
			if (length == 5) break;
			answer++;
		}

		if(answer >= N) answer = -1;
	}

	private static int Search(int x, int y, int dx, int dy, int horse)
	{
		if (array[x + dx][y + dy] != horse) return 0;
		else return Search(x + dx, y + dy, dx, dy, horse) + 1;
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