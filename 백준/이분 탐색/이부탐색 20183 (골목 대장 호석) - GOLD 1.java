import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Main {
	static int A, B, N, M;
	static long answer, C;
	static long[] dist;
	static ArrayList<Edge>[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();
		C = in.nextLong();
		dist = new long[N + 1];
		array = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			array[i] = new ArrayList<Edge>();
		}
		Long max = Long.MIN_VALUE;
		
		for (int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			long c = in.nextLong();
			max = Math.max(max, c);

			array[a].add(new Edge(b,c));
			array[b].add(new Edge(a,c));
		}

		answer = binary(max);
	}

	static private long binary(long max) {
		long answer = -1;
		long l = 0, r = max;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (!Dijkstra(A, mid))
				l = mid + 1;
			else {
				answer = mid;
				r = mid - 1;
			}
		}
		return answer;
	}

	static private boolean Dijkstra(int x, long cost) {
		Arrays.fill(dist, Long.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(x,0));
		dist[x] = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (dist[(int) now.to]<now.weight)
				continue;

			 for (Edge next : array[now.to]) {
				if (cost >= next.weight && dist[next.to] > dist[now.to] + next.weight) {
					dist[next.to] = dist[now.to] + next.weight;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}

		return dist[B] <= C;
	}

}

class Edge implements Comparable<Edge> {
    int to;
    long weight;

    public Edge(int x, long weight) {
        this.to = x;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        // TODO Auto-generated method stub
        return Long.compare(this.weight, o.weight);
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