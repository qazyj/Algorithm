import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static int N, C;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(SearchBinary());
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		C = in.nextInt();

		array = new int[N];
		for (int i = 0; i < N; i++)
			array[i] = in.nextInt();
		Arrays.sort(array);
	}

	private static int SearchBinary() {
		int left = 1;
		int right = array[N - 1] - array[0];
		int d = 0;
		int value = 0;

		while (left <= right) {
			int mid = (left + right) / 2;
			int start = array[0];
			int count = 1;
			for (int i = 0; i < N; i++) { // 집집마다 검색함.
				d = array[i] - start;
				if (d >= mid) { // 만약 첫번째 집과의 거리가 더 크다면 찾았다고 count 올려주고, 내가 찾는집에 이번 집을 넣어준다.
					count++;
					start = array[i];
				}
			}

			if (count >= C) {
				value = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return value;
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