import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, P, S, E, answer;
	static ArrayList<Integer>[] array;
	static int[][] flow, capacity;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		P = in.nextInt();
		S = 1;
		E = 2;
		flow = new int[N+1][N+1];
		capacity = new int[N+1][N+1];

		array = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)
			array[i] = new ArrayList<>();

		for(int i = 0; i < P; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			array[a].add(b);
			array[b].add(a);
			capacity[a][b] = 1;
		}
		EdmondsKarp();
	}

	private static void EdmondsKarp() {
		while(true) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(S);
			int[] parent = new int[N+1];
			Arrays.fill(parent,-1);
			int min = Integer.MAX_VALUE;

			while(!queue.isEmpty()) {
				int now = queue.poll();

				for(int next : array[now]) {
					if(capacity[now][next] - flow[now][next] > 0 && parent[next] == -1) {
						queue.add(next);
						parent[next] = now;
						if(next == E) break;
					}
				}
			}

			if(parent[E] == -1) break;

			for(int i = E; i != S; i = parent[i]) {
				min = Math.min(min, capacity[parent[i]][i] - flow[parent[i]][i]);
			}

			for(int i = E; i != S; i = parent[i]) {
				flow[parent[i]][i] += min;
				flow[i][parent[i]] -= min;
			}

			answer += min;
		}
	}
}

class Door {
	int x;
	int y;

	public Door(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int direction;
	int count;

	public Node(int x, int y, int direction, int count) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.count = count;
	}
	@Override
	public int compareTo(Node o) {
		return this.count - o.count;
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