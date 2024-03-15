import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		boolean[] prime = new boolean[10000];
		prime[0] = prime[1] = true;
		for (int i = 2; i < 10000; i++) {
			if (!prime[i]) {
				for (int j = i+i; j < 10000; j+=i) {
					prime[j] = true;
				}
			}
		}

		int testcase = in.nextInt();
		StringBuilder sb = new StringBuilder();
		while(testcase-->0) {
			int a = in.nextInt();
			int b = in.nextInt();

			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[10000];
			int[] count = new int[10000];

			q.add(a);
			visited[a] = true;

			while(!q.isEmpty()) {
				int num = q.poll();
				if(num == b) break;
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j <= 9; j++) {
						if(i == 0 && j == 0) continue;

						int k = changeNumber(num, i, j);
						if(prime[k] || visited[k]) continue;

						q.add(k);
						visited[k] = true;
						count[k] = count[num] + 1;
					}
				}
			}
			sb.append(count[b]).append("\n");
		}
		System.out.println(sb);
	}

	public static int changeNumber(int num, int i, int j) {
		StringBuilder sb = new StringBuilder(String.valueOf(num));
		sb.setCharAt(i, (char)(j+'0'));
		return Integer.parseInt(sb.toString());
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