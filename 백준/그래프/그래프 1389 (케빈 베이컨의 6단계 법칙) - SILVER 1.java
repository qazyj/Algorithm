import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		int m = in.nextInt();
		List<Integer>[] list = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) list[i] = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			list[a].add(b);
			list[b].add(a);
		}

		int answer = -1;
		int max = 1000000;
		for(int i = 1; i <= n; i++) {
			int[] dis = new int[n+1];
			Arrays.fill(dis, 100000);
			PriorityQueue<Node> pq = new PriorityQueue<>();
			dis[i] = 0;
			pq.add(new Node(i, 0));
			while(!pq.isEmpty()) {
				Node node = pq.poll();

				for(int next : list[node.cur]) {
					if(dis[next] <= node.distance+1) continue;

					dis[next] = node.distance+1;
					pq.add(new Node(next, node.distance+1));
				}
			}

			int sum = 0;
			for(int j = 1; j <= n; j++) sum += dis[j];
			if(sum < max) {
				max = sum;
				answer = i;
			}
		}
		System.out.println(answer);
	}
}

class Node implements Comparable<Node> {
	int cur;
	int distance;

	public Node(int cur, int distance) {
		this.cur = cur;
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