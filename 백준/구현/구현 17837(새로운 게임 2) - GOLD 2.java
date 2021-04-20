import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, K, answer, areaToFive, white, red, blue;
	static int[][] array;
	static Horse[] horses;
	static ArrayList<Integer>[][] horse;
	static Deque<Integer> queue;
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int[] dChange = {0,2,1,4,3};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		K = in.nextInt();
		array = new int[N][N];
		horses = new Horse[K + 1];
		answer = Integer.MAX_VALUE;
		white = 0;
		red = 1;
		blue = 2;
		horse = new ArrayList[N][N];
		answer = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				horse[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			int c = in.nextInt();
			horses[i] = new Horse(a, b, c);
			horse[a][b].add(i);
		}

		solve();
	}

	private static void solve() {
		while (MoveHorse()) {
			answer++;

		}

		if (answer > 1000)
			answer = -1;
	}

	private static boolean MoveHorse() {
		// i번째 인덱스에 해당하는 말을 움직인다.
		for (int i = 1; i <= K; i++) {
			Horse horseT = horses[i];
			int index = -1;
			int size = horse[horseT.x][horseT.y].size();

			// 현재 말 위치 구하기
			for (int j = 0; j < size; j++) {
				if (horse[horseT.x][horseT.y].get(j) == i) {
					index = j;
					break;
				}
			}

			queue = new ArrayDeque();
			// 현재 말과 위에 쌓인말 저장해두기
			for (int j = index; j < size; j++) {
				queue.offer(horse[horseT.x][horseT.y].get(j));
			}
			// 지우기
			for (int j = index; j < size; j++) {
				horse[horseT.x][horseT.y].remove(index);
			}

			// 말의 다음 움직일 위치
			int r = horseT.x + dx[horseT.direction];
			int c = horseT.y + dy[horseT.direction];
			int d = horseT.direction;

			// 체스판을 벗어나거나 파랑색이면
			if (!isInside(r, c) || array[r][c] == 2) {
				// 파란색 영역이면
				// 이동 방향을 바꾸고
				d = dChange[horseT.direction];
				// 한칸 이동
				r += (dx[d] * 2);
				c += (dy[d] * 2);
			}

			if (!isInside(r, c) || array[r][c] == 2) { // 배열 밖을 벗어났다면 그 자리 그대로
				r -= dx[d];
				c -= dy[d];
				while (!queue.isEmpty())
					horse[r][c].add(queue.poll());
			} else if (array[r][c] == white) { // 흰색 영역이면 그대로 위에 쌓는다.
				while (!queue.isEmpty())
					horse[r][c].add(queue.poll());
			} else if (array[r][c] == red) {// 빨간색 영역이면
				while (!queue.isEmpty())
					horse[r][c].add(queue.pollLast());
			}

			if (horse[r][c].size() >= 4 || answer > 1000)
				return false;

			// 옮겨진 말의 값 갱신
			for (int j = 0; j < horse[r][c].size(); j++) {
				int h = horse[r][c].get(j);
				Horse temp = horses[h];
				if (h == i)
					horses[h] = new Horse(r, c, d);
				else
					horses[h] = new Horse(r, c, temp.direction);
			}
		}
		return true;
	}

	public static boolean isInside(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}

class Horse {
	int x;
	int y;
	int direction;

	public Horse(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
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