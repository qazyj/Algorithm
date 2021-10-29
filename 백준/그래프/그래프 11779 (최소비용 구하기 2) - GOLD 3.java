import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[] parent, distance;
	static ArrayList<Node>[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		N = in.nextInt();
		array = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)
			array[i] = new ArrayList<>();

		int count = in.nextInt();

		for(int i = 0; i < count ; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			array[a].add(new Node(b,c));
		}

		int start = in.nextInt();
		int end = in.nextInt();

		dijkstra(start, end);
		sb.append(distance[end]).append("\n");

		Stack<Integer> stack = new Stack<>();
		while(end != 0) {
			stack.add(end);
			end = parent[end];
		}
		sb.append(stack.size()).append("\n");

		while(!stack.isEmpty())
			sb.append(stack.pop() + " ");
	}

	private static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		parent = new int[N+1];
		distance = new int[N+1];
		Arrays.fill(distance, 100000000);
		distance[start] = 0;

		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(now.x == end) break;

			for(Node node : array[now.x]) {
				if(distance[node.x] > distance[now.x] + node.distance){
					distance[node.x] = distance[now.x] + node.distance;
					pq.add(new Node(node.x, distance[node.x]));
					parent[node.x] = now.x;
				}
			}
		}
	}
}

class Node implements Comparable<Node>{
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