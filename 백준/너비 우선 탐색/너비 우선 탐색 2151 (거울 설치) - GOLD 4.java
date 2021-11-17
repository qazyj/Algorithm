import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static char[][] array;
	static Door door1, door2;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][][] check;
	static int[] nextdirection = {2,3,0,1};
	static int[] nextdirection2 = {3,2,1,0};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = Integer.MAX_VALUE;
		array = new char[N][N];
		check = new boolean[N][N][4];

		for (int i = 0; i < N; i++) {
			String s = in.nextLine();
			for (int j = 0; j < N; j++) {
				array[i][j] = s.charAt(j);
				if (door1 == null && array[i][j] == '#') {
					door1 = new Door(i, j);
				} else if (array[i][j] == '#') {
					door2 = new Door(i, j);
				}
			}
		}

		bfs();
	}

	private static void bfs() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		for (int i = 0; i < 4; i++) {
			queue.add(new Node(door1.x, door1.y, i, 0));
		}

		while (!queue.isEmpty()) {

			Node node = queue.poll();

			if (node.x == door2.x && node.y == door2.y) {
				answer = node.count;
				break;
			}

			int x = node.x;
			int y = node.y;
			int direction = node.direction;
			int count = node.count;

			if (check[x][y][direction])
				continue;

			check[x][y][direction] = true;

			int r = x + dx[direction];
			int c = y + dy[direction];

			if(r < 0 || c < 0 || r >= N || c >= N || array[r][c] == '*') continue;

			if (array[r][c] == '!') {
				queue.add(new Node(r, c, nextdirection[direction], count + 1));
				queue.add(new Node(r, c, nextdirection2[direction], count + 1));
			}
			queue.add(new Node(r, c, direction, count));
		}
	}

}

class Door {
	int x;
	int y;

	public Door(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int direction;
	int count;

	public Node(int x, int y, int direction, int count) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.count = count;
	}
	@Override
	public int compareTo(Node o) {
		return this.count - o.count;
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