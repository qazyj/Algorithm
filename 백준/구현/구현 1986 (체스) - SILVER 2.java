import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, answer;
	static boolean[][] horse;
	static boolean[][] check;
	static int[] dx = {-2, -2, -1, 1, -1, 1, 2, 2};
	static int[] dy = {-1, 1, 2, 2, -2, -2, -1, 1};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		horse = new boolean[N][M];
		check = new boolean[N][M];
		answer = N*M;

		Queue<Node> queen = new LinkedList<>();
		int size = in.nextInt();
		answer -= size;
		for(int i = 0 ; i < size; i++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			queen.add(new Node(x, y));
			horse[x][y] = true;
		}

		Queue<Node> knight = new LinkedList<>();
		size = in.nextInt();
		answer -= size;
		for(int i = 0; i < size; i++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			knight.add(new Node(x, y));
			horse[x][y] = true;
		}

		size = in.nextInt();
		answer -= size;
		for(int i = 0; i < size; i++) {
			horse[in.nextInt()-1][in.nextInt()-1] = true;
		}

		while(!queen.isEmpty()) {
			Node q = queen.poll();
			int x, y;

			// 왼
			x = q.x;
			while(x > 0) {
				if(horse[--x][q.y]) break;

				if(check[x][q.y]) continue;
				check[x][q.y] = true;
				answer--;
			}

			// 왼밑
			x = q.x;
			y = q.y;
			while(x > 0 && y < M-1) {
				if(horse[--x][++y]) break;

				if(check[x][y]) continue;
				check[x][y] = true;
				answer--;
			}

			// 밑
			y = q.y;
			while(y < M-1) {
				if(horse[q.x][++y]) break;

				if(check[q.x][y]) continue;
				check[q.x][y] = true;
				answer--;
			}

			// 오밑
			x = q.x;
			y = q.y;
			while(x < N-1 && y < M-1) {
				if(horse[++x][++y]) break;

				if(check[x][y]) continue;
				check[x][y] = true;
				answer--;
			}

			// 오
			x = q.x;
			while(x < N-1) {
				if(horse[++x][q.y]) break;

				if(check[x][q.y]) continue;
				check[x][q.y] = true;
				answer--;
			}

			// 오위
			x = q.x;
			y = q.y;
			while(x < N-1 && y > 0) {
				if(horse[++x][--y]) break;

				if(check[x][y]) continue;
				check[x][y] = true;
				answer--;
			}

			// 위
			y = q.y;
			while(y > 0) {
				if(horse[q.x][--y]) break;

				if(check[q.x][y]) continue;
				check[q.x][y] = true;
				answer--;
			}

			// 왼위
			x = q.x;
			y = q.y;
			while(x > 0 && y > 0) {
				if(horse[--x][--y]) break;

				if(check[x][y]) continue;
				check[x][y] = true;
				answer--;
			}

		}

		while(!knight.isEmpty()) {
			Node node = knight.poll();

			for(int i = 0 ; i < 8; i++) {
				int r = node.x + dx[i];
				int c = node.y + dy[i];

				if(r < 0 || c < 0 || r >= N || c >= M) continue;
				if(horse[r][c] || check[r][c]) continue;

				check[r][c] = true;
				answer--;
			}
		}
	}

}

class Node {
	int x;
	int y;

	public Node(int x, int y){
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