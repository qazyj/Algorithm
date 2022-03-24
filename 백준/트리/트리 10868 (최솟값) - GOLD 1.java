import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] array, input;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	public static void SetData() {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		sb = new StringBuilder();
		array = new int[N*4];
		input = new int[N+1];
		for(int i = 1; i <= N; i++) {
			input[i] = in.nextInt();
		}
		update(1,N,1);


		for(int i = 0; i < M; i++) {
			sb.append(searchMin(1,N,1,in.nextInt(), in.nextInt())).append("\n");
		}
	}

	public static int searchMin(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return array[node];
		}

		int mid = (start + end) / 2;
		return Math.min(searchMin(start, mid, node * 2, left, right), searchMin(mid + 1, end, node * 2 + 1, left, right));
	}

	public static void update(int start, int end, int node) {
		if(start == end) {
			array[node] = input[start];
		}else{
			int mid = (start+end)/2;

			update(start, mid, node*2);
			update(mid+1, end, node*2+1);

			array[node] = Math.min(array[node*2], array[node*2+1]);
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