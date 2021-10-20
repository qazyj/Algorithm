import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] array;
	static int[][] dist;
	static int[] x = {0,0,-1,1};
	static int[] y = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(dist[N-1][M-1]);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		M = in.nextInt();
		N = in.nextInt();
		array = new int[N][M];
		dist = new int[N][M];

		for(int i = 0; i < N; i++) {
			String s = in.nextLine();
			for(int j = 0; j < M; j++) {
				array[i][j] = s.charAt(j) - '0';
				dist[i][j] = 10000000;
			}
		}

		bfs();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0,0));
		dist[0][0] = 0;

		while(!queue.isEmpty()) {
			Node node = queue.poll();

			for(int i = 0; i < 4; i++) {
				int r = node.x + x[i];
				int c = node.y + y[i];

				if(r < 0 || c < 0 || r >= N || c >= M) continue;

				if (dist[r][c] > dist[node.x][node.y] + array[r][c]) {
					dist[r][c] = dist[node.x][node.y] + array[r][c];
					queue.add(new Node(r,c));
				}
			}
		}
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