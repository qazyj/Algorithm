import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Algorithm {
    static int[] array;
    static int N, answer1, answer2;
	
	public static void main(String[] args) throws Exception {
		SetData();
		SearchBinary();
		System.out.println(answer1 + " " + answer2);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
        N = in.nextInt();
		array = new int[N];
		answer1 = answer2 = 0;

		for (int i = 0; i < N; ++i) {
			array[i] = in.nextInt();
		}
		Arrays.sort(array);
	}

	private static void SearchBinary() {
		int min = Integer.MAX_VALUE;
		int left = 0, right = N - 1;
		
		// 이진 탐색
		while (left < right) {
			int sum = array[left] + array[right];

			// v가 최소일 때 특성값 갱신
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				answer1 = array[left];
				answer2 = array[right];
			}

			if (sum > 0)
				right--;
			else
				left++;
		}
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

	public String readString() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
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