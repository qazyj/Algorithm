import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static Node start;
	static char[][] array;
	static boolean[][] check;
	static Map<Character, Object> pipe;
	static Map<String, Character> print;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		array = new char[N][M];
		check = new boolean[N][M];
		pipe = new HashMap<Character, Object>();
		print = new HashMap<String, Character>();
		sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			String s = in.nextLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = s.charAt(j);
				if (array[i][j] == 'M') {
					start = new Node(i, j);
				}
			}
		}

		pipe.put('|', new boolean[] { false, true, false, true });
		pipe.put('-', new boolean[] { true, false, true, false });
		pipe.put('+', new boolean[] { true, true, true, true });
		pipe.put('1', new boolean[] { true, true, false, false });
		pipe.put('2', new boolean[] { true, false, false, true });
		pipe.put('3', new boolean[] { false, false, true, true });
		pipe.put('4', new boolean[] { false, true, true, false });
		print.put("0101", '|');
		print.put("1010", '-');
		print.put("1111", '+');
		print.put("1100", '1');
		print.put("1001", '2');
		print.put("0011", '3');
		print.put("0110", '4');

		sb.append(bfs());
	}

	public static String bfs() {
		Queue<Node> queue = new LinkedList<>();
		check[start.x][start.y] = true;
		for (int i = 0; i < 4; i++) {
			int r = start.x + dx[i];
			int c = start.y + dy[i];
			if (!isRange(r, c) || !isPipe(r, c))
				continue;
			queue.add(new Node(r, c));
			check[r][c] = true;
			break;
		}

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			check[node.x][node.y] = true;
			char now = array[node.x][node.y];
			if (now == '.') {
				String temp = "";
				for (int i = 0; i < 4; i++) {
					int r = node.x + dx[i];
					int c = node.y + dy[i];
					if (!isRange(r, c) || !isPossible(r, c, i))
						temp += "0";
					else
						temp += "1";
				}
				return (node.x + 1) + " " + (node.y + 1) + " " + print.get(temp);
			}
			boolean[] pipeInfo = (boolean[]) pipe.get(now);
			for (int i = 0; i < 4; i++) {
				int nextRow = node.x + dx[i];
				int nextCol = node.y + dy[i];
				if (!isRange(nextRow, nextCol) || check[nextRow][nextCol] || !pipeInfo[i])
					continue;
				queue.add(new Node(nextRow, nextCol));
				check[nextRow][nextCol] = true;
			}
		}
		return "";
	}

	public static boolean isRange(int nextRow, int nextCol) {
		if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
			return false;
		return true;
	}

	public static boolean isPipe(int nextRow, int nextCol) {
		if (array[nextRow][nextCol] == '.')
			return false;
		return true;
	}

	public static boolean isPossible(int row, int col, int i) {
		switch (array[row][col]) {
			case '|':
				if (i == 1 || i == 3)
					return true;
				return false;
			case '-':
				if (i == 0 || i == 2)
					return true;
				return false;
			case '+':
				return true;
			case '1':
				if (i == 0 || i == 1)
					return false;
				return true;
			case '2':
				if (i == 0 || i == 3)
					return false;
				return true;
			case '3':
				if (i == 2 || i == 3)
					return false;
				return true;
			case '4':
				if (i == 1 || i == 2)
					return false;
				return true;
			default:
				return false;
		}
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