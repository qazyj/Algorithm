import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		SetData();
	}

	private static void SetData()throws Exception {
		InputReader in = new InputReader(System.in);

		long[] answer = new long[3];
		int n = in.nextInt();
		long[] liquid = new long[n];

		for (int i=0; i<n; i++) {
			liquid[i] = in.nextLong();
		}

		Arrays.sort(liquid);
		long max = Long.MAX_VALUE;

		for (int i=0; i<n-1; i++) {
			int left = i+1;
			int right = liquid.length-1;

			while(left < right) {
				// 세 용액의 합 계산
				long sum = liquid[i] + liquid[left] + liquid[right];

				// 차이가 더 작다면 원소 저장
				if(Math.abs(sum) < max) {
					max = Math.abs(sum);
					answer[0] = liquid[i];
					answer[1] = liquid[left];
					answer[2] = liquid[right];
				}

				if(sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);

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