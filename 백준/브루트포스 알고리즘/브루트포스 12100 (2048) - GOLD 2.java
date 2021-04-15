import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithm {
	static int N, answer;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = 0;
		array = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				if (answer < array[i][j])
					answer = array[i][j];
			}
		}

		DFS(0);
	}

	private static void DFS(int count) {
		// basecase
		if (count == 5) {
			return;
		}

		int[][] temp = new int[N][N];
		CopyArray(temp, array);
		for(int i=1; i<=4; i++) {
			Move(i);
			DFS(count+1);
			CopyArray(array, temp);
			
		}
	}

	private static void CopyArray(int[][] a, int[][] b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	private static void Move(int direction) {
		switch (direction) {
			case 1:
				for (int j = 0; j < N; j++) {
					int idx = 0;
					for (int i = 1; i < N; i++) {
						if (array[i][j] == 0) continue;
						if (array[idx][j] == array[i][j]) {
							answer = Math.max(answer, array[idx++][j] <<= 1);
							array[i][j] = 0;
						} else if (array[idx][j] == 0) {
							array[idx][j] = array[i][j];
							array[i][j] = 0;
						} else {
							array[++idx][j] = array[i][j];
							if (idx != i) array[i][j] = 0;
						}
						
					}
				}
				break;
			case 2:
				for (int i = 0; i < N; i++) {
					int idx = N - 1;
					for (int j = N - 2; j >= 0; j--) {
						if (array[i][j] == 0) continue;
						if (array[i][idx] == array[i][j]) {
							answer = Math.max(answer, array[i][idx--] <<= 1);
							array[i][j] = 0;
						} else if (array[i][idx] == 0) {
							array[i][idx] = array[i][j];
							array[i][j] = 0;
						} else {
							array[i][--idx] = array[i][j];
							if (idx != j) array[i][j] = 0;
						}
					}
				}
				break;
			case 3:
				for (int j = 0; j < N; j++) {
					int idx = N - 1;
					for (int i = N - 2; i >= 0; i--) {
						if (array[i][j] == 0) continue;
						if (array[idx][j] == array[i][j]) {
							answer = Math.max(answer, array[idx--][j] <<= 1);
							array[i][j] = 0;
						} else if (array[idx][j] == 0) {
							array[idx][j] = array[i][j];
							array[i][j] = 0;
						} else {
							array[--idx][j] = array[i][j];
							if (idx != i) array[i][j] = 0;
						}
					}
				}
				break;
			case 4:
				for (int i = 0; i < N; i++) {
					int idx = 0;
					for (int j = 1; j < N; j++) {
						if (array[i][j] == 0) continue;
						if (array[i][idx] == array[i][j]) {
							answer = Math.max(answer, array[i][idx++] <<= 1);
							array[i][j] = 0;
						} else if (array[i][idx] == 0) {
							array[i][idx] = array[i][j];
							array[i][j] = 0;
						} else {
							array[i][++idx] = array[i][j];
							if (idx != j) array[i][j] = 0;
						}
					}
				}
				break;
		}
	}
}

class Number {
	int number;
	boolean check;

	Number(int number, boolean check) {
		this.number = number;
		this.check = check;
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