import java.io.*;
import java.util.*;

public class Main {
	static int[][] array;
	static boolean[][][] check;
	static int[] dx = { 0, 1, 1, -1 };
	static int[] dy = { 1, 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(0);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		array = new int[20][20];
		check = new boolean[20][20][4];

		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				array[i][j] = in.nextInt();
			}
		}

		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (array[i][j] == 0)
					continue;

				int count;
				if (!check[i][j][0]) {
					count = bfs(i, j, array[i][j], 0);
					if (count == 5) {
						System.out.println(array[i][j]);
						System.out.println(i + " " + j);
						System.exit(0);
					}
				}

				if (!check[i][j][1]) {
					count = bfs(i, j, array[i][j], 1);
					if (count == 5) {
						System.out.println(array[i][j]);
						System.out.println(i + " " + j);
						System.exit(0);
					}
				}

				if (!check[i][j][2]) {
					count = bfs(i, j, array[i][j], 2);

					if (count == 5) {
						System.out.println(array[i][j]);
						System.out.println(i + " " + j);
						System.exit(0);
					}
				}

				if (!check[i][j][3]) {
					count = bfs(i, j, array[i][j], 3);

					if (count == 5) {
						if ((i < 19 && j > 1) && array[i + 1][j - 1] == array[i][j])
							continue;

						System.out.println(array[i][j]);
						System.out.println(i + " " + j);
						System.exit(0);
					}
				}
			}
		}
	}

	private static int bfs(int i, int j, int horse, int direction) {
		Queue<Node> queue = new LinkedList<>();
		check[i][j][direction] = true;
		queue.add(new Node(i, j));

		int count = 1;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			check[node.x][node.y][direction] = true;

			int r = node.x + dx[direction];
			int c = node.y + dy[direction];

			if (r < 1 || c < 1 || r > 19 || c > 19)
				continue;
			if ((direction != 3 && check[r][c][direction]) || array[r][c] != horse)
				continue;

			count++;
			queue.add(new Node(r, c));
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