import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
	static int v,e,s;
	static List<Node>[] list;
	static int[] distance;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		v = in.nextInt();
		e = in.nextInt();
		s = in.nextInt();
		list = new ArrayList[v+1];
		distance = new int[v+1];
		for(int i = 1; i <= v; i++) {
			list[i] = new ArrayList<Node>();
			distance[i] = 200001;
		}

		for(int i = 0; i < e; i++) {
			int s = in.nextInt();
			int e = in.nextInt();
			int w = in.nextInt();

			list[s].add(new Node(e, w));
		}

		bfs();
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= v; i++) {
			if(i==s) sb.append("0");
			else if(distance[i] != 200001) sb.append(distance[i]);
			else sb.append("INF");
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0));
		distance[s] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			for(int i = 0; i < list[node.next].size(); i++) {
				Node next = list[node.next].get(i);

				if(distance[next.next] <= distance[node.next] + next.distance) continue;

				distance[next.next] = distance[node.next] + next.distance;
				pq.add(new Node(next.next, node.distance+next.distance));
			}
		}
	}
}

class Node implements Comparable<Node> {
	int next;
	int distance;

	public Node(int next, int distance) {
		this.next = next;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
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