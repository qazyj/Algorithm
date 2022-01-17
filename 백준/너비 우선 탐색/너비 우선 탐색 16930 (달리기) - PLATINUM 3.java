import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, K, x1, x2, y1, y2, answer;
	static boolean[][] wall;
	static int[][] visit;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		wall = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String s = in.nextLine();
			for (int j = 1; j <= M; j++) {
				if(s.charAt(j - 1)=='#')
					wall[i][j] = true;
			}
		}

		x1 = in.nextInt();
		y1 = in.nextInt();
		x2 = in.nextInt();
		y2 = in.nextInt();
		answer = Integer.MAX_VALUE;
		visit = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs();

		// 이동할 수 없는 경우
		if (answer == Integer.MAX_VALUE)
			answer = -1;
	}

	private static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(x1, y1, 0));
		visit[x1][y1] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (node.x == x2 && node.y == y2) {
				answer = Math.min(answer, node.distance);
				break;
			}

			for (int direction = 0; direction < 4; direction++) {
				for (int k = 1; k <= K; k++) {
					int r = node.x + dx[direction]*k;
					int c = node.y + dy[direction]*k;

					if(r < 1 || c < 1 || r > N || c > M || wall[r][c]) break;

					if (visit[r][c] == Integer.MAX_VALUE) {
						visit[r][c] = node.distance + 1;
						pq.add(new Node(r,c,node.distance + 1));
					}
					else if (visit[r][c] == node.distance + 1) {
						continue;
					}
					else break;
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int distance;

	public Node(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}

	// 거리 오름차순
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.distance - o.distance;
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