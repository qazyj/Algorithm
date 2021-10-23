import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M, answer;
	static ArrayList[] arrayList;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		answer = 0;
		arrayList = new ArrayList[N+1];
		int max = 0;

		for(int i = 1; i <= N; i++)
			arrayList[i] = new ArrayList<Node>();

		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int weight = in.nextInt();

			arrayList[a].add(new Node(b, weight));
			arrayList[b].add(new Node(a, weight));
			max = Math.max(weight, max);
		}

		int x = in.nextInt();
		int y = in.nextInt();

		int left = 0;
		int right = max;
		while(left<= right) {
			int mid = (left + right) / 2;

			check = new boolean[N+1];

			if(bfs(x,y,mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		answer = right;
	}

	private static boolean bfs(int start, int end, int weigth) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		check[start] = true;

		while(!queue.isEmpty()) {
			int d = queue.poll();

			if(d == end) {
				return true;
			}

			for(int i = 0 ; i < arrayList[d].size(); i++) {
				Node node = (Node) arrayList[d].get(i);
				if (!check[node.a] && weigth <= node.weight) {
					check[node.a] = true;
					queue.add(node.a);
				}
			}
		}

		return false;
	}
}

class Node {
	int a;
	int weight;

	public Node(int a, int weight) {
		this.a = a;
		this.weight = weight;
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