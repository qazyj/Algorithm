import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
	static int n;
	static Node[] nodes;
	static boolean[] visited;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		nodes = new Node[n+1];
		visited = new boolean[n+1];
		dp = new int[n+1][2];       // 0 : not al, 1 : al
		for(int i = 1; i < n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			nodes[x] = new Node(y, nodes[x]);   // nodes[x]는 이전 연결되어있는 node를 넣어둠
			nodes[y] = new Node(x, nodes[y]);
		}

		// 트리 구조이기 때문에 1부터 시작
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dfs(int number) {
		visited[number] = true;
		dp[number][0] = 0;      // not al
		dp[number][1] = 1;      // al

		for(Node next = nodes[number]; next != null; next = next.next) {
			if(visited[next.x]) continue;

			dfs(next.x);
			dp[number][0] += dp[next.x][1];	// 자식 노드가 무조건 얼리어답터여야한다.
			dp[number][1] += Math.min(dp[next.x][0], dp[next.x][1]);	// 자식 노드가 얼리어답터 일수도 아닐수도 있다.
		}
	}
}

class Node {
	int x;
	Node next;

	public Node(int x, Node next) {
		this.x = x;
		this.next = next;
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