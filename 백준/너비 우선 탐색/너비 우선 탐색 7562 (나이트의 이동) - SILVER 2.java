import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int[] dx = {-1,-2,1,2,2,1,-2,-1};
	static int[] dy = {-2,-1,-2,-1,1,2,1,2};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();

		int testcase = in.nextInt();
		while(testcase-- > 0) {
			bfs(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		}
	}

	private static void bfs(int n, int x, int y, int a, int b) {
		Queue<Node> node = new LinkedList<>();
		node.add(new Node(x,y, 0));
		boolean[][] check = new boolean[n][n];
		check[x][y] = true;

		int count = Integer.MAX_VALUE;
		while(!node.isEmpty()) {
			Node temp = node.poll();

			if(temp.x == a && temp.y == b)
				count = Math.min(count, temp.count);

			for(int i = 0; i < 8; i++) {
				int r = temp.x + dx[i];
				int c = temp.y + dy[i];

				if(r < 0 || c < 0 || r >= n || c >= n) continue;
				if(check[r][c]) continue;

				check[r][c] = true;
				node.add(new Node(r, c, temp.count+1));
			}
		}

		sb.append(count).append("\n");
	}
}

class Node {
	int x;
	int y;
	int count;

	public Node(int x, int y, int count) {
		this.x = x;
		this.y = y;
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