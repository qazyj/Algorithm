import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static int[][] adj;
	static int[] indegree;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		sb = new StringBuilder();
		adj = new int[N+1][N+1];
		indegree = new int[N+1];

		for(int i = 0; i < M; i++) {
			int temp = in.nextInt();
			int start = in.nextInt();

			for(int j = 0; j < temp-1; j++) {
				int end = in.nextInt();

				if(adj[start][end] == 0) {
					adj[start][end] = 1;
					indegree[end]++;
				}
				start = end;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.add(i);
		}

		while(!queue.isEmpty()) {
			int index = queue.poll();
			sb.append(index).append("\n");

			for(int i = 1; i <= N; i++) {
				if(adj[index][i] == 1) {
					indegree[i]--;

					if(indegree[i] == 0) queue.add(i);
				}
			}
		}

		for(int i = 1; i <= N; i++) {
			if(indegree[i] != 0) {
				sb = new StringBuilder();
				sb.append(0);
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