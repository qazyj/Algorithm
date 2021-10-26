import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int w, h;
	static StringBuilder sb;
	static char[][] array;
	static boolean[][] check;
	static Queue<Sang> sang;
	static Queue<Node> fire;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		int testcase = in.nextInt();
		for (int i = 0; i < testcase; i++) {
			w = in.nextInt();
			h = in.nextInt();
			array = new char[h][w];
			check = new boolean[h][w];
			sang = new LinkedList<>();
			fire = new LinkedList<>();

			for (int a = 0; a < h; a++) {
				String s = in.nextLine();
				for (int b = 0; b < w; b++) {
					array[a][b] = s.charAt(b);
					if (array[a][b] == '*')
						fire.add(new Node(a, b));
					if (array[a][b] == '@')
						sang.add(new Sang(a, b, 0));
				}
			}

			int answer = bfs();
			if (answer == -1) {
				sb.append("IMPOSSIBLE").append("\n");
			} else {
				sb.append(answer).append("\n");
			}
		}

	}

	private static int bfs() {
		while (!sang.isEmpty()) {
			// 불 1초 이동
			int fireSize = fire.size();
			for (int i = 0; i < fireSize; i++) {
				Node node = fire.poll();

				for (int direction = 0; direction < 4; direction++) {
					int r = node.x + dx[direction];
					int c = node.y + dy[direction];

					if (r < 0 || c < 0 || r >= h || c >= w)
						continue;
					if (array[r][c] == '#' || array[r][c] == '*')
						continue;

					array[r][c] = '*';
					fire.add(new Node(r, c));
				}
			}


			// 상근이 1초 이동
			int sangSize = sang.size();

			for (int i = 0; i < sangSize; i++) {
				Sang temp = sang.poll();
				check[temp.x][temp.y] = true;

				for (int direction = 0; direction < 4; direction++) {
					int r = temp.x + dx[direction];
					int c = temp.y + dy[direction];

					if (r < 0 || c < 0 || r >= h || c >= w)
						return temp.count + 1;
					if (array[r][c] != '.' || check[r][c])
						continue;

					check[r][c] = true;
					sang.add(new Sang(r, c, temp.count + 1));
				}
			}
		}

		return -1;
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

class Sang {
	int x;
	int y;
	int count;

	public Sang(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
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