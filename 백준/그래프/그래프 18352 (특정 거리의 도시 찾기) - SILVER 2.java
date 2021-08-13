import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Main {
	static int N, M, K, X;
	private static ArrayList<Integer>[] dijkstra;
	static int[] distance;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		X = in.nextInt();
		sb = new StringBuilder();
		distance = new int[N+1];
		dijkstra = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++)
			dijkstra[i] = new ArrayList<>();

		for (int i = 0; i <= N; i++)
			distance[i] = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			dijkstra[a].add(b);
		}
		
		Dijkstra();

		for (int i = 1; i <= N; i++) {
			if (distance[i] == K)
				sb.append(i).append("\n");
		}

		if (sb.length() == 0)
			sb.append(-1 + "\n");
	}
	
	private static void Dijkstra() {

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

		distance[X] = 0;
		priorityQueue.offer(X);

		while (!priorityQueue.isEmpty()) {

			int n = priorityQueue.poll();

			for(int i = 0; i < dijkstra[n].size(); i++) {

				if (distance[dijkstra[n].get(i)] <= distance[n] + 1) continue;
			
				distance[dijkstra[n].get(i)] = distance[n] + 1;
				priorityQueue.offer(dijkstra[n].get(i));		
			}
		}
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