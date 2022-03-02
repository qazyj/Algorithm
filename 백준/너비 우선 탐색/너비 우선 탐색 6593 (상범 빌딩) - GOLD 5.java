import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int L, R, C;
	static char[][][] array;
	static Queue<Node> queue;
	static int[] dx = {0,0,-1,1,0,0};
	static int[] dy = {-1,1,0,0,0,0};
	static int[] dz = {0,0,0,0,-1,1};
	static boolean[][][] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		while(true) {
			L = in.nextInt();
			R = in.nextInt();
			C = in.nextInt();
			if(L == 0 && R == 0 && C == 0) break;
			array = new char[L][R][C];
			check = new boolean[L][R][C];
			queue = new LinkedList<>();

			for(int i = 0 ; i < L; i++) {
				for(int j = 0; j < R; j++) {
					String s = in.nextLine();
					for(int k = 0; k < C; k++) {
						array[i][j][k] = s.charAt(k);
						if(array[i][j][k] == 'S') {
							queue.add(new Node(i,j,k,0));
							check[i][j][k] = true;
						}
					}
				}
			}

			int value = bfs();
			if(value == -1) {
				sb.append("Trapped!").append("\n");
			}else {
				sb.append("Escaped in "+ value +" minute(s).").append("\n");
			}
		}
	}

	public static int bfs() {
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(array[node.z][node.x][node.y] == 'E') return node.count;

			for(int direction = 0; direction < 6; direction++) {
				int l = node.z + dx[direction];
				int r = node.x + dy[direction];
				int c = node.y + dz[direction];

				if(r < 0 || c < 0 || l < 0 | r >= R || c >= C || l >= L) continue;
				if(check[l][r][c] || array[l][r][c] == '#') continue;

				check[l][r][c] = true;
				queue.add(new Node(l,r,c,node.count+1));
			}
		}
		return -1;
	}
}

class Node {
	int x;
	int y;
	int z;
	int count;

	public Node(int z, int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.z = z;
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