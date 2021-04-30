import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Algorithm {
	static StringBuilder sb;
	static Stack<Integer> stack;
	static Set<String> set;
	static String s;
	static int[] array;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		stack = new Stack<>();
		set = new HashSet<>();

		s = in.nextLine();
		array = new int[s.length()];
		check = new boolean[s.length()];

		int len = s.length();
		for (int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(i);
			} else if (ch == ')') {
				array[i] = stack.peek();
				array[stack.peek()] = i;
				stack.pop();
			}
		}

		dfs(0);
		set.remove(s);
		ArrayList<String> list = new ArrayList<>(set);
		Collections.sort(list);
		for (String temp : list) {
			sb.append(temp).append("\n");
		}
	}

	private static void dfs(int now) {
		// basecase
		if (now == s.length()) {
			set.add(sb.toString());
			return;
		}

		char c = s.charAt(now);
		if (c == '(') {
			check[now] = true;
			dfs(now + 1);
			check[now] = false;
		}

		if (c == ')' && check[array[now]]) {
			check[now] = true;
			dfs(now + 1);
			check[now] = false;
		} else {
			sb.append(c);
			dfs(now + 1);
			sb.deleteCharAt(sb.length() - 1);
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