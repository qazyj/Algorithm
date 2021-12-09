import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static char[][] map = new char[5][9];
	static boolean[] visited = new boolean[13];
	static ArrayList<Node> list = new ArrayList<>();
	static int[][][] p = {
			{{0,4},{1,3},{2,2},{3,1}},
			{{3,1},{3,3},{3,5},{3,7}},
			{{0,4},{1,5},{2,6},{3,7}},
			{{1,1},{1,3},{1,5},{1,7}},
			{{1,1},{2,2},{3,3},{4,4}},
			{{4,4},{3,5},{2,6},{1,7}}
	};
	static int size;
	static boolean check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			char[] temp = in.nextLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = temp[j];
				if(temp[j] =='x') {
					list.add(new Node(i, j));
				}
				else if(temp[j] != '.'){
					visited[temp[j]-'A'+1] = true;
				}
			}
		}

		size = list.size();

		solve(0,0);
	}

	private static void solve(int count, int index) {
		if(check) return ;

		if(count == size && check()) {
			check = true;
			for (int i = 0; i < 5; i++) {
				sb.append(map[i]).append("\n");
			}
			return ;
		}

		for (int j = 1; j <= 12; j++) {
			if(visited[j]) continue;

			Node cur = list.get(index);
			visited[j] = true;
			map[cur.x][cur.y] = (char)(j+64);
			solve(count+1, index+1);
			visited[j] = false;
			map[cur.x][cur.y] = 'x';
		}
	}

	private static boolean check() {
		for (int a = 0; a < 6; a++) {
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				sum += (map[p[a][i][0]][p[a][i][1]] -'A'+1);
			}
			if(sum != 26) return false;
		}
		return true;
	}

}

class Node {
	int x;
	int y;

	Node(int x, int y) {
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