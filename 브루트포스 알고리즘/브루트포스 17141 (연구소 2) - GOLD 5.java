import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int N, M, count, min;
	private static int[][] array;
	private static boolean[] check;
	private static ArrayList<int[]> virus;
	private static int[] x = { 0, 0, -1, 1 };
	private static int[] y = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(min);
	}
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		array = new int[N][N];
		virus = new ArrayList<>();
		min = Integer.MAX_VALUE;
		count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
				if (array[i][j] == 2)
					virus.add(new int[] { i, j });
				if (array[i][j] == 0)
					count++;
			}
		}
		count += virus.size() - M;
		check = new boolean[virus.size()];
		if (count != 0)
			dfs(0, 0);
		else
			min = 0;
		
		if(min == Integer.MAX_VALUE)
			min = -1;
	}

	private static void dfs(int start, int depth) {
		// Basecase
		if (depth == M) {
			int[][] tempArray = copyArray();
			bfs(tempArray, count);
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			check[i] = true;
			dfs(i+1, depth + 1);
			check[i] = false;
		}
	}

	private static void bfs(int[][] tempArray, int count) {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			if (check[i])
				queue.add(virus.get(i));
		}
		
		int time = 0;
		while (!queue.isEmpty()) {
			if (time >= min)
				break;
			int len = queue.size();
			for (int j = 0; j < len; j++) {
				int[] location = queue.poll();
				for (int direction = 0; direction < 4; direction++) {
					int r = location[0] + x[direction];
					int c = location[1] + y[direction];
					if (r >= 0 && c >= 0 && r < N && c < N) {
						if (tempArray[r][c] == 0) {
							tempArray[r][c] = 2;
							queue.add(new int[] { r, c });
							count--;
						}
					}
				}
			}
			
			time++;
			if (count == 0) {
				min = time;
				return;
			}
		}
	}

	private static int[][] copyArray() {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(array[i], 0, result[i], 0, N);
		}
		for (int i = 0; i < virus.size(); i++) {
			if (!check[i]) {
				int[] location = virus.get(i);
				result[location[0]][location[1]] = 0;
			}
		}
		return result;
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