import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static char[] arr;
	static List<Node> lists;
	static PriorityQueue<String> pq;
	static Set<String> set;
	static int n;;
	static boolean[] checked;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		arr = in.nextLine().toCharArray();
		n = arr.length;
		checked = new boolean[n];
		Stack<Integer> stack = new Stack<>();
		lists = new ArrayList<>();
		for(int i = 0 ; i < n; i++) {
			if(arr[i] == '(') {
				stack.push(i);
			} else if(arr[i] == ')') {
				lists.add(new Node(stack.pop(), i));
			}
		}

		pq = new PriorityQueue<>();
		set = new HashSet<>();
		dfs(0, false);

		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) sb.append(pq.poll()).append("\n");
		System.out.println(sb);
	}

	public static void dfs(int index, boolean isRemove) {
		if(index == lists.size()) {
			if(!isRemove) return;
			StringBuilder temp = new StringBuilder();
			for(int i = 0 ; i < n; i++) {
				if(checked[i]) continue;

				temp.append(arr[i]);
			}
			if(set.contains(temp.toString())) return;
			set.add(temp.toString());
			pq.add(temp.toString());
			return;
		}

		Node node = lists.get(index);
		checked[node.x] = true;
		checked[node.y] = true;
		dfs(index+1, true);
		checked[node.x] = false;
		checked[node.y] = false;
		dfs(index+1, isRemove);
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