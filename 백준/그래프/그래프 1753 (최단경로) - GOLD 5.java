import java.util.*;

public class Main {
	static int V,E,s;
	static StringBuilder sb;
	static ArrayList<Node>[] node;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		V = in.nextInt();
		E = in.nextInt();
		s = in.nextInt();
		sb = new StringBuilder();
		node = new ArrayList[V + 1];
		dist = new int[V + 1];

		Arrays.fill(dist, 100000000);

		for(int i = 1; i <= V; i++){
			node[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < E; i++){
			int start = in.nextInt();
			int end = in.nextInt();
			int weight = in.nextInt();

			node[start].add(new Node(end, weight));
		}

		dijkstra(s);

		for(int i = 1; i <= V; i++){
			if(dist[i] == 100000000) sb.append("INF\n");
			else sb.append(dist[i] + "\n");
		}
	}

	private static void dijkstra(int start){
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[V + 1];
		queue.add(new Node(start, 0));
		dist[start] = 0;

		while(!queue.isEmpty()){
			Node curNode = queue.poll();
			int cur = curNode.e;

			if(check[cur] == true) continue;
			check[cur] = true;

			for(Node node : node[cur]){
				if(dist[node.e] > dist[cur] + node.w){
					dist[node.e] = dist[cur] + node.w;
					queue.add(new Node(node.e, dist[node.e]));
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int e;
	int w;

	public Node(int e, int w) {
		this.e = e;
		this.w = w;
	}

	@Override
	public int compareTo(Node node) {
		return this.w - node.w;
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