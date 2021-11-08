import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static int[][] array;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		array = new int[N][M];
		check = new boolean[N][M];
		sb = new StringBuilder();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();

			}
		}

		bfs();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		Queue<Node> melt = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		int a = 0, b = 0, count = 0, size = 0;

		while(true) {
			for(int i = 0; i < N; i++)
				Arrays.fill(check[i], false);
			queue.add(new Node(0, 0));
			check[0][0] = true;
			while(!queue.isEmpty()) {
				Node node = queue.poll();

				for(int direction = 0; direction < 4; direction++) {
					int r = node.x + dx[direction];
					int c = node.y + dy[direction];

					if(r < 0 || c < 0 || r >= N || c >= M) continue;
					if(check[r][c]) continue;
					if(array[r][c] == 1) {
						if(!visit[r][c]) {
							visit[r][c] = true;
							melt.add(new Node(r,c));
						}
						continue;
					}

					queue.add(new Node(r, c));
					check[r][c] = true;
				}
			}
			if(melt.size() == 0) break;

			size = melt.size();
			while(!melt.isEmpty()) {
				Node node = melt.poll();
				array[node.x][node.y] = 0;
			}

			count++;
		}
		sb.append(count).append("\n").append(size);
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