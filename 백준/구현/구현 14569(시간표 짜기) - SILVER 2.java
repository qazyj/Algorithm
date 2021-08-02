import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Stack;

public class Algorithm {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		sb = new StringBuilder();
		Long[] t = new Long[N];
		
		for (int i = 0; i < N; i++) {
			int p, q;
			t[i] = 0L;
			p = in.nextInt();
			for (int j = 0; j < p; j++) {
				q = in.nextInt(); 
				t[i] |= (1L << q);
				
			}
		}
		
		int M = in.nextInt();
		
		for (int i = 0; i < M; i++) {
			int p, q, count = 0;
			Long tmp = 0L;
			p = in.nextInt();
			for (int j = 0; j < p; j++) {
				q= in.nextInt(); 
				tmp |= (1L << q);
			}
			tmp = ~tmp;
			for (int j = 0; j < N; j++) {
				if (((tmp & t[j]) == 0L)) count++;
			}
			sb.append(count).append("\n");
		}
	}
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