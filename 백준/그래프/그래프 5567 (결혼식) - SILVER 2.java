import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<Integer>[] list = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) list[i] = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			list[a].add(b);
			list[b].add(a);
		}

		Set<Integer> set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1, 0));
		set.add(1);
		while(!q.isEmpty()) {
			Node node = q.poll();

			for(int next : list[node.cur]) {
				if(set.contains(next)) continue;

				set.add(next);
				if(node.distance == 1) continue;
				q.add(new Node(next, node.distance+1));
			}
		}
		System.out.println(set.size()-1);
	}
}

class Node{
	int cur;
	int distance;

	public Node(int cur, int distance) {
		this.cur = cur;
		this.distance = distance;
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