import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, H, answer;
	static int[][][] array;
	static boolean[][][] check;
	static Queue<Node> queue;
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dh = {0,0,0,0,-1,1};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		M = in.nextInt();
		N = in.nextInt();
		H = in.nextInt();
		array = new int[H][N][M];
		check = new boolean[H][N][M];
		queue = new LinkedList<>();
		for(int i = 0 ; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int z = 0; z < M; z++) {
					array[i][j][z] = in.nextInt();
					if(array[i][j][z] == 1) {
						queue.add(new Node(j,z,i));
						check[i][j][z] = true;
					}
				}
			}
		}
		bfs();
		for(int i = 0 ; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int z = 0; z < M; z++) {
					if(array[i][j][z] == 0) {
						answer = -1;
						break;
					}
				}
			}
		}
		if(answer != -1) answer--;
	}

	public static void bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();

			while(size-->0) {
				Node node = queue.poll();
				for(int direction = 0; direction < 6; direction++) {
					int r = node.x + dx[direction];
					int c = node.y + dy[direction];
					int h = node.h + dh[direction];

					if(r < 0 || c < 0 || h < 0 || r >= N || c >= M || h >= H) continue;
					if(array[h][r][c] != 0 || check[h][r][c]) continue;

					check[h][r][c] = true;
					array[h][r][c] = 1;
					queue.add(new Node(r,c,h));
				}
			}
			answer++;
		}
	}
}

class Node {
	int x;
	int y;
	int h;

	public Node(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
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