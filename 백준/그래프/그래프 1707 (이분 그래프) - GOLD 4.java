import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static ArrayList[] array;
	static boolean[] check, color;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for(int i = 0 ; i < testcase; i++) {
			int V = in.nextInt();
			int E = in.nextInt();
			array = new ArrayList[V + 1];
			check = new boolean[V+1];
			color = new boolean[V+1];

			for (int j = 1; j <= V; j++) {
				array[j] = new ArrayList<Integer>();
			}

			for(int j = 0 ; j < E; j++) {
				int u = in.nextInt();
				int v = in.nextInt();

				array[u].add(v);
				array[v].add(u);
			}

			for (int j = 1; j <= V; j++) {
				if (check[j]) continue;

				bfs(j, false);
			}

			boolean isBipartite = false;
			for (int j = 1; j <= V; j++) {
				for(int a = 0 ; a < array[j].size(); a++) {
					if (color[j] == color[(int) array[j].get(a)]) {
						isBipartite = true;
						break;
					}
				}
			}

			if (!isBipartite) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
	}

	private static void bfs(int index, boolean nowColor) {
		check[index] = true;
		color[index] = nowColor;

		for(int i = 0 ; i < array[index].size(); i++) {
			int value = (int) array[index].get(i);

			if(check[value]) continue;
			bfs(value, !nowColor);
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