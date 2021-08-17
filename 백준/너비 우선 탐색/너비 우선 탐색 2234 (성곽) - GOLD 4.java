import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m, numberOfRoom, BiggestRoom, removeWallBiggestRoom;
	static int[][] array;
	static int[][] group;
	static boolean[][] check;
	static int[] x = { 0, -1, 0, 1 };
	static int[] y = { -1, 0, 1, 0 };
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(numberOfRoom);
		System.out.println(BiggestRoom);
		System.out.println(removeWallBiggestRoom);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		m = in.nextInt();
		n = in.nextInt();
		array = new int[n][m];
		check = new boolean[n][m];
		group = new int[n][m];
		numberOfRoom = 0;
		BiggestRoom = 0;
		removeWallBiggestRoom = 0;
		map = new HashMap<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				array[i][j] = in.nextInt();
			}
		}

		int groupCount = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (check[i][j])
					continue;

				int count = bfs(i, j, groupCount);
				numberOfRoom++;
				map.put(groupCount++, count);
				BiggestRoom = Math.max(BiggestRoom, count);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				removeWall(i, j);
			}
		}
	}

	private static int bfs(int i, int j, int groupCount) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { i, j });
		check[i][j] = true;
		group[i][j] = groupCount;
		int count = 1;
		while (!queue.isEmpty()) {

			int[] location = queue.poll();
			for (int direction = 0; direction < 4; direction++) {
				if ((array[location[0]][location[1]] & (1 << direction)) != 0)				continue;

				int r = location[0] + x[direction];
				int c = location[1] + y[direction];
				
				if (r < 0 || r >= n || c < 0 || c >= m || check[r][c])				continue;

				count++;
				check[r][c] = true;
				group[r][c] = groupCount;
				queue.add(new int[] { r, c });
			}
		}
		return count;
	}

	private static void removeWall(int i, int j) {
		int sum = map.get(group[i][j]);

		for (int direction = 0; direction < 4; direction++) {

			int r = i + x[direction];
			int c = j + y[direction];

			if (r < 0 || r >= n || c < 0 || c >= m)			continue;

			if ((array[i][j] & (1 << direction)) == 0 || group[i][j] == group[r][c])			continue;
			
			sum += map.get(group[r][c]);
			removeWallBiggestRoom = Math.max(removeWallBiggestRoom, sum);
			sum -= map.get(group[r][c]);
		}
	}
}

class Cow {
	int x;
	int y;

	public Cow(int x, int y) {
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