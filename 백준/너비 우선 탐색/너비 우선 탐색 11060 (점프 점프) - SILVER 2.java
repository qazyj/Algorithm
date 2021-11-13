import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N];
		for(int i = 0; i < N; i++)
			array[i] = in.nextInt();

		answer = bfs();

	}

	private static int bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		boolean[] check = new boolean[N];

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.index == N-1) {
				return node.countOfJump;
			}

			for(int i = 1; i <= array[node.index]; i++) {
				if(node.index+i >= N) continue;
				if(check[node.index + i]) continue;

				check[node.index + i] = true;
				pq.add(new Node(node.index + i, node.countOfJump+1));
			}
		}
		return -1;
	}
}

class Node implements Comparable<Node> {
	int index;
	int countOfJump;

	public Node(int index, int countOfJump) {
		this.index = index;
		this.countOfJump = countOfJump;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.countOfJump - o.countOfJump;
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