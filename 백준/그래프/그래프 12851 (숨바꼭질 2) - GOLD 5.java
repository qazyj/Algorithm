import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int answer, N, K, minCount;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(answer + "\n" + minCount);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		answer = Integer.MAX_VALUE;
		N = in.nextInt();
		K = in.nextInt();
		check = new boolean[100001];
		minCount = 0;

		bfs(N, K, 0);
	}

	private static void bfs(int a, int b, int count) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(a, 0));

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			check[node.index] = true;
			if(node.index == b) {
				if(answer > node.count) {
					minCount = 1;
					answer = node.count;
				} else if(answer == node.count) {
					minCount++;
				}
			}

			if(node.index < b) {
				if(node.index+1 <= 100000 && !check[node.index+1])
					queue.add(new Node(node.index+1, node.count+1));
				if(node.index*2 <= 100000 && !check[node.index*2])
					queue.add(new Node(node.index*2, node.count+1));
			}
			if(node.index - 1 >= 0 && !check[node.index-1])
				queue.add(new Node(node.index-1, node.count +1));
		}
	}
}

class Node {
	int index;
	int count;

	public Node(int index, int count) {
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