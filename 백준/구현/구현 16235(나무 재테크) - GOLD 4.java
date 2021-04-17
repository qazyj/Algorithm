import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, M, K;
	static int[][] array, foodForTree;
	static ArrayList<Tree> tree;
	static ArrayList<Tree> livedTree;
	static ArrayList<Tree> deadTree;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(tree.size());
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		array = new int[N + 1][N + 1];
		tree = new ArrayList<>();
		foodForTree = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				foodForTree[i][j] = in.nextInt();
				array[i][j] = 5;
			}
		}

		for(int i = 0; i < M; i++)
			tree.add(new Tree(in.nextInt(), in.nextInt(), in.nextInt()));

		Solve();
	}

	private static void Solve() {
		while (K-- > 0) {
			livedTree = new ArrayList<>();
			deadTree = new ArrayList<>();
			Collections.sort(tree);	// 정렬
			ComeSpring();
			ComeSummer();
			ComeFall();
			ComeWinter();
		}
	}

	private static void ComeSpring() {
		for (int i = 0; i < tree.size(); i++) {			
			Tree t = tree.get(i);
			
			// 양분이 충분한 경우 양분을 먹는다.
			if(array[t.x][t.y] >= t.age) {
				array[t.x][t.y] -= t.age;
				t.age++;				
				livedTree.add(t);
			} else {
				// 그렇지 못한 경우 죽음
				deadTree.add(t);
			}
		}
		tree.clear();
		tree.addAll(livedTree);
	}

	private static void ComeSummer() {
		// 죽은 나무 양분
		for (int i = 0; i < deadTree.size(); i++) {
			Tree t = deadTree.get(i);
			array[t.x][t.y] += t.age / 2;
		}
	}

	private static void ComeFall() {
		for (int i = 0; i < tree.size(); i++) {
			if(tree.get(i).age % 5 != 0) continue;
			
			int x = tree.get(i).x;
			int y = tree.get(i).y;
			
			for(int direction = 0; direction < 8; direction++) {
				int r = x + dx[direction];
				int c = y + dy[direction];
				if(r <= 0 || c <= 0 || r > N || c > N) continue;
				
				tree.add(new Tree(r, c, 1));
			}
		}
	}

	private static void ComeWinter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				array[i][j] += foodForTree[i][j];
			}
		}
	}
}

class Tree implements Comparable<Tree>{
	int x;
	int y;
	int age;

	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	
	@Override
	public int compareTo(Tree t) {
		return this.age - t.age;
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