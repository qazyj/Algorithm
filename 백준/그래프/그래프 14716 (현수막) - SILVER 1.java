import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		m = in.nextInt();
		arr = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				arr[i][j] = in.nextInt();
			}
		}

		int answer = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0 ; j < m; j++) {
				if(visited[i][j] || arr[i][j] == 0) continue;

				answer++;
				bfs(i,j);
			}
		}
		System.out.println(answer);
	}

	public static void bfs(int i, int j) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		visited[i][j] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();

			for(int d = 0; d < 8; d++) {
				int r = node.x + dx[d];
				int c = node.y + dy[d];

				if(r < 0 || c < 0 || r >= n || c >= m) continue;
				if(visited[r][c] || arr[r][c] == 0) continue;

				visited[r][c] = true;
				q.add(new Node(r,c));
			}
		}
	}
}

class Node{
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