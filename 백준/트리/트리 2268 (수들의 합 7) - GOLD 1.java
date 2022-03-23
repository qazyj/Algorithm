import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
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
		long[] array = new long[N*4];

		for(int i = 0; i < M; i++) {
			int work = in.nextInt();
			int start = in.nextInt();
			int end = in.nextInt();

			if (work == 0) {
				if(start>end) {
					int temp = start;
					start = end;
					end = temp;
				}
				sb.append(sum(array, 1, N, 1, start, end)).append("\n");
			} else if (work == 1) {
				update(array, 1, N, 1, start, end);
			}
		}
	}

	public static long sum(long[] array, int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return array[node];
		}

		int mid = (start + end) / 2;
		return sum(array, start, mid, node * 2, left, right) + sum(array, mid + 1, end, node * 2 + 1, left, right);
	}

	public static long update(long[] array, int start, int end, int node, int idx, int val) {
		if (idx < start || idx > end) {
			return array[node];
		}

		// 리프 노드
		if (start == end) {
			return array[node] = val;
		}

		int mid = (start + end) / 2;

		// 리프 노드와 연결된 트리의 가지 전체를 업데이트
		return array[node] = update(array, start, mid, node * 2, idx, val) + update(array, mid + 1, end, node * 2 + 1, idx, val);

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