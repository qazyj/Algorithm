import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, K, answer;
	static boolean[][] array;
	static boolean[][] check;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static Queue<Node> queue;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		array = new boolean[N+1][M+1];
		check = new boolean[N+1][M+1];
		queue = new LinkedList<>();
		answer = -1;

		for(int i = 0; i < K; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			array[r][c] = true;
			queue.add(new Node(r,c));
		}

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(check[node.x][node.y]) continue;

			answer = Math.max(answer, bfs(node.x , node.y));
		}
	}

	private static int bfs(int x , int y) {
		int count = 1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));
		check[x][y] = true;

		while(!q.isEmpty()) {
			Node node = q.poll();

			for(int direction = 0; direction < 4; direction++) {
				int r = node.x + dx[direction];
				int c = node.y + dy[direction];

				if(r <= 0 || c <= 0 || r > N || c > M) continue;
				if(!array[r][c] || check[r][c]) continue;

				check[r][c] = true;
				count++;
				q.add(new Node(r,c));
			}
		}

		return count;
	}
}

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
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