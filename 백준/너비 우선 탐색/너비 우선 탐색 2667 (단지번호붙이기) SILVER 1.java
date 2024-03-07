import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		arr = new int[n][n];
		visited = new boolean[n][n];


		for(int i = 0 ; i < n; i++) {
			String s = in.nextLine();
			for(int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] == 0 || visited[i][j]) continue;

				pq.add(bfs(i,j));
			}
		}

		System.out.println(pq.size());
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		System.out.println(sb);
	}

	public static int bfs(int i, int j) {
		int count = 1;
		visited[i][j] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i,j));
		while(!q.isEmpty()) {
			Node node = q.poll();

			for(int d = 0; d < 4; d++) {
				int r = node.x + dx[d];
				int c = node.y + dy[d];

				if(r < 0 || c < 0 || r >= n || c >= n) continue;
				if(visited[r][c] || arr[r][c] == 0) continue;

				visited[r][c] = true;
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