import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] array;
	static int[] rArray;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		sb = new StringBuilder();
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();
			}
		}
		rArray = new int[R];
		for (int i = 0; i < R; i++)
			rArray[i] = in.nextInt();

		for (int r : rArray) {
			if (r == 1) {
				InputOne();
			} else if (r == 2) {
				InputTwo();
			} else if (r == 3) {
				array = InputThree();
			} else if (r == 4) {
				array = InputFour();
			} else if (r == 5) {
				InputFive();
			} else if (r == 6) {
				InputSix();
			}

			N = array.length;
			M = array[0].length;
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sb.append(array[i][j] + " ");
			}
			sb.append("\n");
		}
	}

	private static void InputOne() {
		int max = N - 1;
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] += array[max][j];
				array[max][j] = array[i][j] - array[max][j];
				array[i][j] -= array[max][j];
			}
			max--;
		}
	}

	private static void InputTwo() {
		int max = M - 1;
		for (int i = 0; i < M / 2; i++) {
			for (int j = 0; j < N; j++) {
				array[j][i] += array[j][max];
				array[j][max] = array[j][i] - array[j][max];
				array[j][i] -= array[j][max];
			}
			max--;
		}
	}

	private static int[][] InputThree() {
		int[][] result = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[j][N - i - 1] = array[i][j];
			}
		}
		return result;
	}

	private static int[][] InputFour() {
		int[][] result = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[M - j - 1][i] = array[i][j];
			}
		}
		return result;
	}

	private static void InputFive() {
		int[][] temp = new int[N / 2][M / 2];

		// 4사분면 temp 저
		int index1 = 0;
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[index1][j] = array[i][j];
			}
			index1++;
		}

		// 3 -> 4
		for (int i = N / 2; i < N; i++) {
			int index = 0;
			for (int j = M / 2; j < M; j++) {
				array[i][index++] = array[i][j];
			}
		}

		// 2 -> 3
		for (int i = 0; i < N / 2; i++) {
			int index = N / 2 + i;
			for (int j = M / 2; j < M; j++) {
				array[index][j] = array[i][j];
			}
		}

		// 1 -> 2
		for (int i = 0; i < N / 2; i++) {
			int index = M / 2;
			for (int j = 0; j < M / 2; j++) {
				array[i][index++] = array[i][j];
			}
		}

		// 4-> 1
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				array[i][j] = temp[i][j];
			}
		}
	}

	private static void InputSix() {
		int[][] temp = new int[N / 2][M / 2];

		// 1사분면 temp 저장
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[i][j] = array[i][j];
			}
		}

		// 2 -> 1
		for (int i = 0; i < N/2; i++) {
			int index = 0;
			for (int j = M / 2; j < M; j++) {
				array[i][index++] = array[i][j];
			}
		}

		// 3 -> 2
		int index1 = 0;
		for(int i = N/2; i < N; i++) {
			for(int j = M/2; j < M; j++) {
				array[index1][j] = array[i][j];
			}
			index1++;
		}

		// 4 -> 3
		for(int i = N/2; i < N; i++) {
			int index = M/2;
			for(int j = 0; j < M/2; j++) {
				array[i][index++] = array[i][j];
			}
		}

		// 1 -> 4
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < M/2; j++) {
				array[N/2 + i][j]= temp[i][j];
			}
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