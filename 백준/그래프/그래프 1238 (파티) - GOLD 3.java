import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N,M,X,answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		answer = 0;
		N = in.nextInt();
		M = in.nextInt();
		X = in.nextInt();
		ArrayList<Node>[] array = new ArrayList[N+1];
		ArrayList<Node>[] reArray = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)  {
			array[i] = new ArrayList<>();
			reArray[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int w = in.nextInt();
			array[a].add(new Node(b, w));
			reArray[b].add(new Node(a, w));
		}

		int[] distance = dijkstra(array);
		int[] nDistance = dijkstra(reArray);

		for(int i = 1; i <= N; i++) {
			answer = Math.max(answer, distance[i] + nDistance[i]);
		}
	}

	private static int[] dijkstra(ArrayList<Node>[] array) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0));

		boolean[] check = new boolean[N + 1];
		int[] distance = new int[N + 1];
		Arrays.fill(distance, 10000000);

		distance[X] = 0;

		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();

			if (check[nowNode.x])  continue;

			check[nowNode.x] = true;
			for(int i = 0; i < array[nowNode.x].size(); i++) {
				Node node = array[nowNode.x].get(i);
				if (check[node.x] || distance[node.x] <= distance[nowNode.x] + node.distance) continue;

				distance[node.x] = distance[nowNode.x] + node.distance;
				pq.add(new Node(node.x, distance[node.x]));
			}
		}
		return distance;

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
	public int compareTo(Node node) {
		// TODO Auto-generated method stub
		return this.distance - node.distance;
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