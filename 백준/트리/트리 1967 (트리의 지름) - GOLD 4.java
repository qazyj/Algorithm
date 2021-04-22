import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Algorithm {
	static int n, answer, maxIndex;
	static ArrayList<Node>[] list;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		list = new ArrayList[n + 1];
		maxIndex = 0;
		answer = 0;

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int parent = in.nextInt();
			int child = in.nextInt();
			int weight = in.nextInt();
			list[parent].add(new Node(child, weight));
			list[child].add(new Node(parent, weight));
		}

		check = new boolean[n + 1];
		check[1] = true;
		dfs(1, 0);

		check = new boolean[n + 1];
		check[maxIndex] = true;
		dfs(maxIndex, 0);
	}

	public static void dfs(int index, int count) {
		// basecase
		if (answer < count) {
			answer = count;
			maxIndex = index;
		}

		for (Node node : list[index]) {
			if (!check[node.index]) {
				check[node.index] = true;
				dfs(node.index, count + node.count);
			}
		}
	}
}

class Node {
	int index, count;

	Node(int index, int count) {
		this.index = index;
		this.count = count;
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