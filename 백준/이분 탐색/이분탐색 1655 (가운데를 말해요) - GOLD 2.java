import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Algorithm {
	static int N;
	static ArrayList<Integer> array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		sb = new StringBuilder();
		array = new ArrayList<Integer>();
		for(int i = 0 ; i < N; i++) {
			int value = in.nextInt();
			int index = BinarySearch(0, i, value);
			array.add(index, value);
			if(array.size() >= 2 && array.size() % 2 == 0)
				sb.append(array.get(array.size()/2-1)).append("\n");
			else
				sb.append(array.get(array.size()/2)).append("\n");
		}
	}

	private static int BinarySearch(int a, int b, int key) {
		if(b == 0)
			return b;
		
		while (a <= b) {
			int mid = (a + b) / 2;
			if(mid >= array.size())
				return mid;
			
			if (key == array.get(mid)) {
				return mid;
			} else {
				if (key < array.get(mid)) {
					b = mid - 1;
				} else {
					a = mid + 1;
				}
			}
		}
		return a;
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