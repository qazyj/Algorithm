import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] array;
	static boolean[][] visit;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		N = in.nextInt();
		M = in.nextInt();
		array = new char[N+1][M+1];
		visit = new boolean[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			String s = in.nextLine();
			for(int j = 1; j <= M; j++) {
				array[i][j] = s.charAt(j-1);
			}
		}

		int count = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(array[i][j] == '*') {
					String s = bfs(i,j);
					if(s.length() > 0) {
						count++;
						temp.append(s);
					}
				}
			}
		}

		if(count == 0) {
			sb.append(-1);
		} else {
			boolean check = false;
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(array[i][j] == '*' && !visit[i][j]) {
						check = true;
						break;
					}
				}
			}

			if(!check)
				sb.append(count).append("\n").append(temp);
			else
				sb.append(-1);
		}
	}

	private static String bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(x, y, 1));
		queue.add(new Node(x, y, 2));
		queue.add(new Node(x, y, 3));
		queue.add(new Node(x, y, 4));

		int count = 0;
		while(!queue.isEmpty()) {
			boolean check = false;

			for(int i = 0; i < 4; i++) {
				Node node = queue.poll();

				if(node.direction == 1) {
					if(node.x > 1 && array[node.x-1][node.y] == '*') {
						queue.add(new Node(node.x-1, node.y, 1));
					} else {
						check = true;
						break;
					}
				} else if(node.direction == 2) {
					if(node.y > 1 && array[node.x][node.y-1] == '*') {
						queue.add(new Node(node.x, node.y-1, 2));
					} else {
						check = true;
						break;
					}
				} else if(node.direction == 3) {
					if(node.x < N && array[node.x+1][node.y] == '*') {
						queue.add(new Node(node.x+1, node.y, 3));
					} else {
						check = true;
						break;
					}
				} else if(node.direction == 4) {
					if(node.y < M && array[node.x][node.y+1] == '*') {
						queue.add(new Node(node.x, node.y+1, 4));
					} else {
						check = true;
						break;
					}
				}
			}

			if(check) break;
			count++;
		}

		if(count == 0) {
			return "";
		} else {
			for(int i = count; i >= 1; i--) {
				visit[x][y] = true;
				visit[x-i][y] = true;
				visit[x+i][y] = true;
				visit[x][y-i] = true;
				visit[x][y+i] = true;
			}
			return x+" "+y+" "+count+"\n";
		}
	}
}

class Node {
	int x;
	int y;
	int direction;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
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