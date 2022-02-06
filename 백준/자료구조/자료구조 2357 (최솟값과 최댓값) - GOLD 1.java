import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, NN;
	static StringBuilder sb;
	static int[] array;
	static int[] treeMax;
	static int[] treeMin;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		array = new int[N+1];
		sb = new StringBuilder();
		int NN = 1;
		while (N > NN) {
			NN *= 2;
		}
		treeMax = new int[NN * 2+1];
		treeMin = new int[NN * 2+1];

		for(int i = 1; i <= N; i++) {
			array[i] = in.nextInt();
		}
		InitMax(1, N, 1);
		InitMin(1, N, 1);

		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			sb.append(QueryMin(1, N, 1, a, b) + " " + QueryMax(1, N, 1, a, b) + "\n");
		}
	}

	// 최소값
	private static int InitMin(int start, int end, int now) {
		// 리프노드 (1~1)
		if (start == end)
			return treeMin[now] = array[start];

		int mid = (start + end) / 2;
		int a = InitMin(start, mid, 2 * now);
		int b = InitMin(mid + 1, end, 2 * now + 1);
		return treeMin[now] = (a < b) ? a : b;
	}

	private static int QueryMin(int start, int end, int now, int left, int right) {
		// 탐색하는 트리범위 내에 탐색 대상 노드(left,right)가 없을 경우 (범위 밖)
		// (1) left~right start~end (2) start~end left~right
		if (right < start || end < left)
			return Integer.MAX_VALUE;
		// 탐색하는 트리범위와 탐색 대상 노드(left,right)가 겹칠 경우 경우 (범위 내)
		// left start~end right
		if (left <= start && end <= right)
			return treeMin[now];

		int mid = (start + end) / 2;
		int a = QueryMin(start, mid, 2 * now, left, right);
		int b = QueryMin(mid + 1, end, 2 * now + 1, left, right);
		return (a < b) ? a : b;
	}

	// 최대값
	private static int InitMax(int start, int end, int now) {
		// 리프노드 (1~1)
		if (start == end)
			return treeMax[now] = array[start];

		int mid = (start + end) / 2;
		int a = InitMax(start, mid, 2 * now);
		int b = InitMax(mid + 1, end, 2 * now + 1);
		return treeMax[now] = (a > b) ? a : b;
	}

	private static int QueryMax(int start, int end, int now, int left, int right) {
		// 탐색하는 트리범위 내에 탐색 대상 노드(left,right)가 없을 경우 (범위 밖)
		// (1) left~right start~end (2) start~end left~right
		if (right < start || end < left)
			return Integer.MIN_VALUE;
		// 탐색하는 트리범위와 탐색 대상 노드(left,right)가 겹칠 경우 경우 (범위 내)
		// left start~end right
		if (left <= start && end <= right)
			return treeMax[now];

		int mid = (start + end) / 2;
		int a = QueryMax(start, mid, 2 * now, left, right);
		int b = QueryMax(mid + 1, end, 2 * now + 1, left, right);
		return (a > b) ? a : b;
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