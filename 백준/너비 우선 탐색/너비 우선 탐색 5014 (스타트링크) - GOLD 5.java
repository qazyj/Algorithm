import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static String answer;
	static int[] array;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		String[] s = in.nextLine().split(" ");

		int F = Integer.parseInt(s[0]);
		int S = Integer.parseInt(s[1]);
		int G = Integer.parseInt(s[2]);
		int U = Integer.parseInt(s[3]);
		int D = Integer.parseInt(s[4]);
		array = new int[F + 1];

		answer = bfs(F, S, G, U, D);
	}

	public static String bfs(int Floor, int start, int end, int up, int down) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		array[start] = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current == end) {
				return String.valueOf(array[current] - 1);
			}

			if (current + up <= Floor) {
				if (array[current + up] == 0) {
					array[current + up] = array[current] + 1;
					queue.add(current + up);
				}

			}

			if (current - down > 0) {
				if (array[current - down] == 0) {
					array[current - down] = array[current] + 1;
					queue.add(current - down);
				}
			}

		}
		return "use the stairs";
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