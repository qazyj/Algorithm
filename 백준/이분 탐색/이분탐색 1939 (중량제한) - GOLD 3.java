import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static int N, M, start, end, answer;
	static List<Node>[] lists;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		lists = new ArrayList[N+1];
		answer = 0;
		for(int i = 1; i <= N; i++) lists[i] = new ArrayList<Node>();

		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();

			lists[a].add(new Node(b,c));
			lists[b].add(new Node(a,c));
			left = Math.min(left, c);
			right = Math.max(right, c);
		}

		start = in.nextInt();
		end = in.nextInt();

		while(left <= right) {
			int middle = (left + right) / 2;
			if(bfs(middle)) {
				left = middle + 1;
				answer = middle;
			}
			else right = middle - 1;
		}
		System.out.println(answer);
	}

	public static boolean bfs(int weight) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(new Node(start, 0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.next == end) return true;
			for(int i = 0; i < lists[node.next].size(); i++) {
				Node next = lists[node.next].get(i);
				if(weight <= next.weight && !visited[next.next]) {
					visited[next.next] = true;
					q.add(next);
				}
			}
		}
		return false;
	}
}

class Node{
	int next;
	int weight;

	public Node(int next,int weight) {
		this.next = next;
		this.weight = weight;
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