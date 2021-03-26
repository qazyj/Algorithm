import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static int N;
	static int[] min, max, array, temp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(Math.max(max[2], Math.max(max[0], max[1]))+" "+Math.min(min[2], Math.min(min[0], min[1])));
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		N =  in.nextInt();
		min = new int [3];
		max = new int [3];
		array = new int [3];
		temp = new int [3];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) 
				array[j] = in.nextInt();
			temp[0] = array[0] + Math.min(min[0], min[1]);
			temp[1] = array[1] + Math.min(min[2], Math.min(min[0], min[1]));
			temp[2] = array[2] + Math.min(min[2], min[1]);
			min[0] = temp[0];
			min[1] = temp[1];
			min[2] = temp[2];
			temp[0] = array[0] + Math.max(max[0], max[1]);
			temp[1] = array[1] + Math.max(max[2], Math.max(max[0], max[1]));
			temp[2] = array[2] + Math.max(max[2], max[1]);
			max[0] = temp[0];
			max[1] = temp[1];
			max[2] = temp[2];
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