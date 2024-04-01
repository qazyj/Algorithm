import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static char[] arr;
	static List<Integer> list;
	static List<String> answer;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		arr = new char[n];
		String[] s = in.nextLine().split(" ");
		for(int i = 0 ; i < n; i++) arr[i] = s[i].charAt(0);
		list = new ArrayList<>();
		answer = new ArrayList<>();

		for(int i = 9; i >= 0 ; i--) {
			list.add(i);
			dfs(0);
			list.remove(Integer.valueOf(i));
		}
		Collections.sort(answer);
		System.out.println(answer.get(answer.size()-1));
		System.out.println(answer.get(0));
	}

	public static void dfs(int index) {
		if(index == n) {
			StringBuilder sb = new StringBuilder();
			for(int i : list) {
				sb.append(i);
			}
			answer.add(sb.toString());
			return;
		}

		int cur = list.get(index);
		for(int i = 9; i >= 0; i--) {
			if(list.contains(i)) continue;

			if(arr[index] == '>') {
				if(cur <= i) continue;

				list.add(i);
				dfs(index+1);
				list.remove(Integer.valueOf(i));
			} else if (arr[index] == '<') {
				if(cur >= i) continue;

				list.add(i);
				dfs(index+1);
				list.remove(Integer.valueOf(i));
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