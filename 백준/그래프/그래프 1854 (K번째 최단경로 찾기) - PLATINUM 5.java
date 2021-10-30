import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int N, M, K;
	static ArrayList<Node>[] array;
	static PriorityQueue<Integer>[] distance;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();

		array = new ArrayList[N + 1];
		distance = new PriorityQueue[N + 1];
		for (int i = 0; i <= N; i++) {
			array[i] = new ArrayList<>();
			distance[i] = new PriorityQueue<>(Collections.reverseOrder());
		}

		for (int i = 0; i < M; i++) {
			array[in.nextInt()].add(new Node(in.nextInt(), in.nextInt()));
		}

		dijkstra();

		for (int i = 1; i <= N; i++) {
			if (distance[i].size() == K)
				sb.append(distance[i].peek()).append("\n");
			else
				sb.append("-1").append("\n");
		}

	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[1].add(0);
		pq.add(new Node(1, 0));

		while(!pq.isEmpty()) {
			Node temp = pq.poll();

			for(Node node : array[temp.x]) {
				if(distance[node.x].size() < K) {
					distance[node.x].add(temp.distance + node.distance);
					pq.add(new Node(node.x, temp.distance + node.distance));
				} else if(distance[node.x].peek() > temp.distance + node.distance) {
					distance[node.x].poll();
					distance[node.x].add(temp.distance + node.distance);
					pq.add(new Node(node.x, temp.distance + node.distance));
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int x;
	int distance;

	public Node(int x, int distance) {
		this.x = x;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
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