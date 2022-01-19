import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] array;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N][N];
		answer = Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				min = Math.min(min, array[i][j]);
				max = Math.max(max, array[i][j]);
			}
		}

		int s = 0;
		int e = max-min;
		while(s <= e) {
			int mid = (s+e)/2;
			boolean isRight = false;

			for(int i = min; i <= max; i++) {
				if(i > array[0][0] || array[0][0] > i+mid) continue;

				if(bfs(i, i+mid)) {
					isRight = true;
					break;
				}
			}

			if(isRight) {
				answer = Math.min(answer, mid);
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
	}

	private static boolean bfs(int minV, int maxV) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0,0));
		boolean[][] check = new boolean[N][N];
		check[0][0] = true;

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.x == N-1 && node.y == N-1) return true;

			for(int direction = 0; direction < 4; direction++) {
				int r = node.x + dx[direction];
				int c = node.y + dy[direction];

				if(r < 0 || c < 0 || r >= N || c >= N || check[r][c]) continue;
				if(array[r][c] < minV || array[r][c] > maxV) continue;

				check[r][c] = true;
				queue.add(new Node(r,c));
			}
		}
		return false;
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