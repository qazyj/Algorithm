import java.io.*;
import java.util.*;

public class Main {
	static int N, M, max;
	static int[][] array;
	static boolean[][] check;
	static StringBuilder sb;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		array = new int[N][M];
		check = new boolean[N][M];
		sb = new StringBuilder();
		max = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();
			}
		}

		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(check[i][j] || array[i][j] == 0) continue;

				count++;
				max = Math.max(max, bfs(i,j));
			}
		}

		sb.append(count).append("\n").append(max);
	}

	private static int bfs(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		check[i][j] = true;
		queue.add(new Node(i,j));

		int count = 1;
		while(!queue.isEmpty()) {
			Node node = queue.poll();

			for(int direction = 0; direction < 4; direction++) {
				int r = node.x + dx[direction];
				int c = node.y + dy[direction];

				if(r < 0 || c < 0 || r >= N || c >= M) continue;
				if(check[r][c] || array[r][c] == 0) continue;

				count++;
				check[r][c] = true;
				queue.add(new Node(r,c));
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