import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static char[] array;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for (int i = 0; i < testcase; i++) {
			String word = in.nextLine();
			array = word.toCharArray();

			if (SaveAnswer()) {
				for (int j = 0; j < word.length(); j++) {
					sb.append(array[j]);
				}
				sb.append("\n");
			} else
				sb.append(word + "\n");
		}
	}

	private static boolean SaveAnswer() {
		// 교환 위치 찾기
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		if (i == 0) return false; // i가 0이라면 정렬 끝으로 다음 순열이 없으므로 false 리턴

		// 교환할 위치 찾기
		int j = array.length - 1;
		while (array[i - 1] >= array[j])
			--j;

		// 교환
		char temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// 교환 위치 이후 값 정렬
		int k = array.length - 1;
		while (i < k) {
			temp = array[i];
			array[i] = array[k];
			array[k] = temp;
			i++;
			k--;
		}
		return true;
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