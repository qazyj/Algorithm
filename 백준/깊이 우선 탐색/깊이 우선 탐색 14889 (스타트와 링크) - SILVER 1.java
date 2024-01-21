import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
	static int[][] arr;
	static boolean[] visited;
	static int N, answer;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = Integer.MAX_VALUE;
		arr = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = in.nextInt();
			}
		}

		dfs(0, 0);
		System.out.println(answer);
	}

	public static void dfs(int idx, int depth) {
		if(depth == N / 2) {
			int startTeam = 0, linkTeam = 0;

			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (visited[i] && visited[j]) {
						startTeam += arr[i][j];
						startTeam += arr[j][i];
					}
					else if (!visited[i] && !visited[j]) {
						linkTeam += arr[i][j];
						linkTeam += arr[j][i];
					}
				}
			}

			// 두 팀의 점수 차이 (절댓값)
			int gap = Math.abs(startTeam - linkTeam);

			if (gap == 0) {
				System.out.println(gap);
				System.exit(0);
			}

			answer = Math.min(gap, answer);
			return;
		}

		for (int i = idx; i < N; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			dfs(i + 1, depth + 1);
			visited[i] = false;
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