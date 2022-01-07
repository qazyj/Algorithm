import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int answer, N, M;
	static ArrayList<Node> node;

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
		node = new ArrayList<>();

		int testcase = in.nextInt()+1;
		for (int i = 0; i < testcase; i++) {
			int direction = in.nextInt();
			int length = in.nextInt();
			if(direction==1) {
				node.add(new Node(0, length));
			}else if(direction==2) {
				node.add(new Node(M, length));
			}else if(direction==3) {
				node.add(new Node(length, 0));
			}else if(direction==4) {
				node.add(new Node(length,N));
			}
		}
		search(testcase-1);
	}

	private static void search(int testcase) {
		for (int i = 0; i < testcase; i++) {
			if(Math.abs(node.get(testcase).x-node.get(i).x)==M) {
				answer+=M;
				answer+=Math.min(node.get(testcase).y+node.get(i).y, 2*N-node.get(testcase).y-node.get(i).y);
			}else if(Math.abs(node.get(testcase).y-node.get(i).y)==N) {
				answer+=N;
				answer+=Math.min(node.get(testcase).x+node.get(i).x, 2*M-node.get(testcase).x-node.get(i).x);
			}else {
				answer+=Math.abs(node.get(testcase).x-node.get(i).x);
				answer+=Math.abs(node.get(testcase).y-node.get(i).y);
			}
		}
	}
}

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
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