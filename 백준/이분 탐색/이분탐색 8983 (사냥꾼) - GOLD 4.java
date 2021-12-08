import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int M, N, L, answer;
	static int[] array;
	static ArrayList<Animals> animals;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		M = in.nextInt();
		N = in.nextInt();
		L = in.nextInt();

		array = new int[M];
		for (int i = 0; i < M; i++) {
			array[i] = in.nextInt();
		}
		Arrays.sort(array);

		animals = new ArrayList<Animals>();
		for (int i = 0; i < N; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			animals.add(new Animals(c, r));
		}

		answer = 0;
		for (int i = 0; i < N; i++)
			answer += search(i);
	}

	private static int search(int index) {
		int left = 0;
		int right = M;
		int mid = 0;

		while (left <= right) {
			mid = (left + right) / 2;

			if (mid >= M) {
				return 0;
			}

			int dist = getDis(array[mid], animals.get(index));

			if ((L < dist) && animals.get(index).col < array[mid]) {
				right = mid - 1;
			}
			else if (L < dist && animals.get(index).col >= array[mid]) {
				left = mid + 1;
			}
			else if (L >= dist) {
				return 1;
			}
		}

		return 0;
	}

	private static int getDis(int value, Animals animal) {
		return (Math.abs(value - animal.col) + animal.row);
	}

}

class Animals {
	int row;
	int col;

	public Animals(int row, int col) {
		this.row = row;
		this.col = col;
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