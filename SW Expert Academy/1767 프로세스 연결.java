import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Algorithm {
	static int[][] array;
	static ArrayList<int[]> core;
	static int N, answer, maxOfConnectedCore;
	static int[] x = { -1, 0, 1, 0 };
	static int[] y = { 0, 1, 0, -1 };
	static StringBuilder sb;


	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();	//testcase
		sb = new StringBuilder();

		for (int i = 1; i <= testcase; i++) {
			N = in.nextInt();
			array = new int[N][N];
			answer = Integer.MAX_VALUE;
			core = new ArrayList<>();

			InputArray(in);

			maxOfConnectedCore = 0;
			dfs(0, 0, 0);
			sb.append("#" + i + " " + answer).append("\n");
		}
	}

	private static void dfs(int depth, int countOfConnectedCore, int countOfLine) {
		// basecase
		if (depth == core.size()) {
			if (maxOfConnectedCore < countOfConnectedCore) {
				maxOfConnectedCore = countOfConnectedCore;
				answer = countOfLine;
			} else if (maxOfConnectedCore == countOfConnectedCore) {
				if (answer > countOfLine)
					answer = countOfLine;
			}

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (CheckConnect(core.get(depth), i)) {
				int count = ConnectLine(core.get(depth), i, 2); // 연결
				dfs(depth + 1, countOfConnectedCore + 1,countOfLine + count);
				ConnectLine(core.get(depth), i, 0); // 연결 해제
			}
		}
		
		dfs(depth + 1, countOfConnectedCore, countOfLine);	// 연결 X
	}

	private static void InputArray(InputReader in) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				if (array[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1)
					core.add(new int[] { i, j });
			}
		}
	}

	private static int ConnectLine(int[] coreIndex, int direction, int value) {
		int count = 0;
		int r = coreIndex[0] + x[direction];
		int c = coreIndex[1] + y[direction];
		while (r >= 0 && c >= 0 && r < N && c < N) {
			array[r][c] = value;
			count++;
			r = r + x[direction];
			c = c + y[direction];
		}
		return count;
	}

	private static boolean CheckConnect(int[] coreIndex, int direction) {
		int r = coreIndex[0] + x[direction];
		int c = coreIndex[1] + y[direction];
		while (r >= 0 && c >= 0 && r < N && c < N) {
			if (array[r][c] != 0)
				return false;
			r = r + x[direction];
			c = c + y[direction];
		}
		return true;
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