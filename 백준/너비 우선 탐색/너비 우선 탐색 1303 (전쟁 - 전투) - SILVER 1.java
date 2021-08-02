import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] array;
	static boolean[][] check;
	static int N, M, friendly, enemy;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(friendly + " " + enemy);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		M = in.nextInt();
		N = in.nextInt();
		array = new char[N][M];
		check = new boolean[N][M];
		friendly = 0;
		enemy = 0;
		
		for(int i = 0; i < N; i++) {
			String s = in.nextLine();
			for(int j = 0 ; j < M; j++) {
				array[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(check[i][j])  continue;
				
				if(array[i][j] == 'W')
					friendly += bfs(i, j, 'W');
				else if(array[i][j] == 'B')
					enemy += bfs(i, j, 'B');
			}
		}
	}
	
	private static int bfs(int i, int j, char peerIdentification) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { i, j });
		check[i][j] = true;
		int count = 1;

		while (!queue.isEmpty()) {
			int location[] = queue.poll();

			for (int direction = 0; direction < 4; direction++) {
				int r = location[0] + x[direction];
				int c = location[1] + y[direction];

				if (r >= 0 && c >= 0 && r < N && c < M) {
					if (array[r][c] == peerIdentification && !check[r][c]) {
						queue.offer(new int[] { r, c });
						check[r][c] = true;
						count++;
					}
				}
			}
		}
		
		return count*count;
	}
	
	/*
	private static int SaveAnswer(int index, char c) {
		if(s.length() <= index)
			return 0;
		
		if(c == '(') {
			if(s.charAt(index) == ')')
				return 2;
			else if(s.charAt(index) == '(')
				return 2 * SaveAnswer(index + 1, '(');
			else if(s.charAt(index) == '[')
				return 2 * SaveAnswer(index + 1, '[');
			else 
				return 0;
		}
		else if(c == '[') {
			if(s.charAt(index) == ']')
				return 3;
			else if(s.charAt(index) == '(')
				return 3 * SaveAnswer(index + 1, '(');
			else if(s.charAt(index) == '[')
				return 3 * SaveAnswer(index + 1, '[');
			else
				return 0;
		} else {
			if(s.charAt(index) == '(')
				return SaveAnswer(index + 1, '(');
			else if(s.charAt(index) == '[')
				return SaveAnswer(index + 1, '[');
		}
		
		return 0;
	}*/
}

class Node implements Comparable<Node> {
	long age;
	int index;

	public Node(long age, int index) {
		this.age= age;
		this.index = index;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Long.compare(this.age, o.age);
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