import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int R, C, answer;
	static char[][] array;
	static Node one, two;
	static boolean[][] check;
	static Queue<Node> water, queue;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		R = in.nextInt();
		C = in.nextInt();
		answer = 0;
		array = new char[R][C];
		check = new boolean[R][C];
		water = new LinkedList<>();
		queue = new LinkedList<>();

		for(int i = 0; i < R; i++) {
			String s = in.nextLine();
			for(int j = 0; j < C; j++) {
				array[i][j] = s.charAt(j);
				if(array[i][j] == 'L') {
					if(one == null)
						one = new Node(i, j);
					else
						two = new Node(i, j);
				}
				if(array[i][j] != 'X') {
					water.add(new Node(i, j));
				}
			}
		}
		queue.add(one);
		check[one.x][one.y] = true;
		while(bfs()) {
			Melt();
			answer++;
		}
	}

	private static void Melt() {
		int size = water.size();

		for(int i = 0 ; i < size ; i++) {
			Node now = water.poll();

			for(int direction = 0 ; direction < 4 ; direction++) {
				int r = now.x + dx[direction];
				int c = now.y + dy[direction];

				if(r < 0 || r >= R || c < 0 || c >= C) continue;
				if(array[r][c] != 'X') continue;

				array[r][c] = '.';
				water.add(new Node(r, c));

			}
		}
	}

	private static boolean bfs() {
		Queue<Node> temp = new LinkedList<>();

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.x == two.x && node.y == two.y) return false;

			for(int direction = 0; direction < 4; direction++) {
				int r = node.x + dx[direction];
				int c = node.y + dy[direction];

				if(r < 0 || r >= R || c < 0 || c >= C) continue;
				if(check[r][c]) continue;
				check[r][c] = true;
				if(array[r][c] == 'X') {
					temp.add(new Node(r, c));
					continue;
				}

				queue.add(new Node(r, c));
			}
		}
		queue = temp;
		return true;
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