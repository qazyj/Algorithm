import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int N, M, x, y, K;
	static int[][] array;
	static int[] dice;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static StringBuilder sb;
	static Queue<Integer> queue;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		K = in.nextInt();
		array = new int[N][M];
		sb = new StringBuilder();
		queue = new LinkedList<>();
		dice = new int[7];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < K; i++) {
			queue.add(in.nextInt());
		}

		direction();
	}

	private static void direction() {
		while (!queue.isEmpty()) {
			int d = queue.poll();
			int r = x + dx[d - 1];
			int c = y + dy[d - 1];

			if (r < 0 || c < 0 || r >= N || c >= M)
				continue;

			RollDice(d);
			
			if (array[r][c] == 0) {
				array[r][c] = dice[6];
			} else {
				dice[6] = array[r][c];
				array[r][c] = 0;
			}
			x = r;
			y = c;

			sb.append(dice[1]).append("\n");
		}
	}

	private static void RollDice(int direction) {
		int[] temp = new int[7];
		for (int i = 1; i <= 6; i++) {
			temp[i] = dice[i];
		}

		switch (direction) {
		case 1: // 동
			dice[1] = temp[2];
			dice[3] = temp[1];
			dice[6] = temp[3];
			dice[2] = temp[6];
			break;
		case 2: // 서
			dice[1] = temp[3];
			dice[2] = temp[1];
			dice[6] = temp[2];
			dice[3] = temp[6];
			break;
		case 3: // 북
			dice[4] = temp[1];
			dice[6] = temp[4];
			dice[5] = temp[6];
			dice[1] = temp[5];
			break;
		case 4: // 남
			dice[5] = temp[1];
			dice[6] = temp[5];
			dice[4] = temp[6];
			dice[1] = temp[4];
			break;
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