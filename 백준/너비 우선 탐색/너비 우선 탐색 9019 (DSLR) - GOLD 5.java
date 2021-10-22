import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for(int i = 0; i < testcase; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			check = new boolean[10000];

			sb.append(bfs(a,b)).append("\n");
		}
	}

	private static String bfs(int a, int b) {
		Queue<Node> queue = new LinkedList<>();
		check[a] = true;
		queue.add(new Node(a, ""));

		while(!queue.isEmpty()) {
			Node node = queue.poll();

			if(node.value == b) {
				return node.command;
			}

			int valueAfterCommand = D(node.value);
			if(!check[valueAfterCommand]) {
				check[valueAfterCommand] = true;
				queue.add(new Node(valueAfterCommand, node.command+"D")) ;
			}

			valueAfterCommand = S(node.value);
			if(!check[valueAfterCommand]) {
				check[valueAfterCommand] = true;
				queue.add(new Node(valueAfterCommand, node.command+"S")) ;
			}

			valueAfterCommand = L(node.value);
			if(!check[valueAfterCommand]) {
				check[valueAfterCommand] = true;
				queue.add(new Node(valueAfterCommand, node.command+"L")) ;
			}

			valueAfterCommand = R(node.value);
			if(!check[valueAfterCommand]) {
				check[valueAfterCommand] = true;
				queue.add(new Node(valueAfterCommand, node.command+"R")) ;
			}
		}

		return "";
	}

	private static int D(int a) {
		return (a*2)%10000;
	}

	private static int S(int a) {
		a -=1;
		if(a==-1)
			return 9999;
		else
			return a;
	}

	private static int L(int a) {
		int temp = a%1000;
		int temp1 = a/1000;
		return temp*10+temp1;
	}

	private static int R(int a) {
		int temp = a % 10;
		int temp1 = a / 10;
		return temp*1000+temp1;
	}
}

class Node {
	int value;
	String command;

	public Node(int value, String command) {
		this.value = value;
		this.command = command;
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