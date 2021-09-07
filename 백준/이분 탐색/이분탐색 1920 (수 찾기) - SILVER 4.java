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

		int n = in.nextInt();
		int[]array = new int[n];
		sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			array[i] = in.nextInt();
		}
		Arrays.sort(array);
		int m = in.nextInt();
		for (int i = 0; i < m; i++) {
			int num = in.nextInt();
			int left = 0;
			int right = array.length - 1;
			boolean flag = false;
			while (left <= right) {
				int index = (left + right) / 2;
				int mid = array[index];

				if (mid > num) {
					right = index - 1;
				} else if (mid < num) {
					left = index + 1;
				} else {
					flag = true;
					sb.append(1).append("\n");
					break;
				}
			}
			if (!flag) {
				sb.append(0).append("\n");
			}
		}
	}
}

class Node implements Comparable<Node> {
	int data;

	public Node(int data) {
		this.data = data;
	}

	@Override
	public int compareTo(Node node) {
		return node.data - this.data;
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