import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static int n,m;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int sheep, wolf;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		m = in.nextInt();
		arr = new char[n][m];
		visited = new boolean[n][m];
		sheep = 0;
		wolf = 0;
		for(int i = 0; i < n; i++) {
			String s = in.nextLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visited[i][j] || arr[i][j] == '#') continue;

				bfs(i,j);
			}
		}
		System.out.println(sheep + " " + wolf);
	}

	public static void bfs(int i,int j) {
		Queue<Node> q = new LinkedList<>();
		visited[i][j] = true;
		q.add(new Node(i,j));
		int s = 0, w = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if(arr[cur.x][cur.y] == 'v') w++;
			if(arr[cur.x][cur.y] == 'k') s++;

			for(int d = 0 ; d < 4; d++) {
				int r = cur.x + dx[d];
				int c = cur.y + dy[d];

				if(r < 0 || c < 0 || r >= n || c >= m) continue;
				if(visited[r][c] || arr[r][c] == '#') continue;

				visited[r][c] = true;
				q.add(new Node(r,c));
			}
		}

		if(s > w) w = 0;
		else s = 0;
		sheep += s;
		wolf += w;
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