import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Algorithm {
	static int N, x, y, answer;
	static int[][] array;
	static Shark shark;
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { 1, 0, 0, -1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N][N];
		answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				if (array[i][j] == 9) {
					shark = new Shark(i,j,0);
					array[i][j] = 0;
				}
			}
		}

		bfs();
	}

	private static void bfs() {
		int size = 2;
		int eat = 0; // 먹은 물고기 수

		while (true) {
			PriorityQueue<Shark> queue = new PriorityQueue<>();
			boolean[][] check = new boolean[N][N];

			queue.add(new Shark(shark.x, shark.y, 0)); // x좌표, y좌표, 이동한 거리
			check[shark.x][shark.y] = true;

			boolean sharkEat = false;

			while (!queue.isEmpty()) {
				shark = queue.poll();

				if (array[shark.x][shark.y] != 0 && array[shark.x][shark.y] < size) { // 먹이가 있으면서 상어의
					array[shark.x][shark.y] = 0; // 물고기 제거
					eat++;
					answer += shark.distance; // 움직인 거리를 총 움직인 거리에 추가
					sharkEat = true;
					break;
				}

				for (int direction = 0; direction < 4; direction++) {
					int r = shark.x + dx[direction];
					int c = shark.y + dy[direction];

					if (r < 0 || c < 0 || c >= N || r >= N || check[r][c] || array[r][c] > size)
						continue;

					queue.add(new Shark(r, c, shark.distance + 1));
					check[r][c] = true;
				}
			}

			if (!sharkEat) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
				break;

			if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
				size++;
				eat = 0;
			}
		}

	}
}

class Shark implements Comparable<Shark> {
	int x;
	int y;
	int distance;

	public Shark(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}

	@Override
	public int compareTo(Shark o) {
		if(this.distance != o.distance)
			return Integer.compare(this.distance, o.distance);
		else {
			if(this.x != o.x)
				return Integer.compare(this.x, o.x);
			else
				return Integer.compare(this.y, o.y);
		}
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