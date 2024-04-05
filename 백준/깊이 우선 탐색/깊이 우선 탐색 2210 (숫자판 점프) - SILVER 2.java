import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	static char[][] arr;
	static Set<String> set;
	static List<Character> list;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		arr = new char[5][5];
		for(int i = 0 ; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				arr[i][j] = (char) in.nextInt();
			}
		}

		set = new HashSet<>();
		list = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dfs(i,j);
			}
		}
		System.out.println(set.size());
	}

	public static void dfs(int x, int y) {
		if(list.size() == 6) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < list.size(); i++) sb.append(list.get(i));
			if(!set.contains(sb.toString())) set.add(sb.toString());
			return;
		}

		for(int d = 0; d < 4; d++) {
			int r = dx[d] + x;
			int c = dy[d] + y;

			if(r < 0 || c < 0 || r >= 5 || c >= 5) continue;
			list.add(arr[r][c]);
			dfs(r, c);
			list.remove(list.size()-1);
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