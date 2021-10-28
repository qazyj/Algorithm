import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[][] array;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		int index = 1;
		while(true) {
			N = in.nextInt();
			if(N == 0) break;

			array = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					array[i][j] = in.nextInt();
				}
			}

			sb.append("Problem "+(index++)+ ": "+ dijkstra()).append("\n");
		}
	}

	private static int dijkstra() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0));

		boolean[][] check = new boolean[N][N];
		int[][] distance = new int[N][N];

		for(int i = 0; i < N; i++)
			Arrays.fill(distance[i], 150000);

		distance[0][0] = array[0][0];
		check[0][0] = true;

		while (!queue.isEmpty()) {
			Node nowNode = queue.poll();

			for(int direction = 0; direction < 4; direction++) {
				int r = nowNode.x + dx[direction];
				int c = nowNode.y + dy[direction];

				if(r < 0 || c < 0 || r >= N || c >= N) continue;
				if(check[r][c] || distance[r][c] <= distance[nowNode.x][nowNode.y] + array[r][c]) continue;

				distance[r][c] = distance[nowNode.x][nowNode.y] + array[r][c];
				queue.add(new Node(r, c));
			}
		}
		return distance[N-1][N-1];

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