import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static ArrayList[] node;
	static int answer, max;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int V = in.nextInt();
		node = new ArrayList[V+1];
		check = new boolean[V+1];
		answer = 0;
		max = 0;

		for(int i = 1; i <= V ;i++)
			node[i] = new ArrayList<Node>();

		for(int i = 0; i < V; i++) {
			int now = in.nextInt();

			while(true) {
				int nodeNumber = in.nextInt();
				if(nodeNumber == -1) break;
				int distance = in.nextInt();

				node[now].add(new Node(nodeNumber, distance));
			}
		}

		check[1] = true;
		bfs(1, 0);
		check[1] = false;

		check[max] = true;
		bfs(max, 0);
	}

	private static void bfs(int now, int sum) {
		if(answer < sum) {
			answer = sum;
			max = now;
		}

		for(int i = 0; i < node[now].size(); i++) {
			Node temp = (Node) node[now].get(i);

			if(check[temp.x]) continue;

			check[temp.x] = true;
			bfs(temp.x, sum + temp.distance);
			check[temp.x] = false;
		}
	}
}

class Node {
	int x;
	int distance;

	public Node(int x, int distance) {
		this.x = x;
		this.distance = distance;
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