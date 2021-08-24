import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Stack;

public class Main {
	static int N, K, answer;
	static int[] s;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		K = in.nextInt();
		answer = 0;

		int antic = 0;

		for (int i = 0; i < 5; i++) 
			antic |= (1 << ("antic".charAt(i) - 'a'));
		
		
		s = new int[N];
		for (int i = 0; i < N; i++) {
			String temp = in.nextLine();
			int bit = antic;
			
			for (int j = 4; j < temp.length() - 4; j++) 
				bit |= (1 << temp.charAt(j) - 'a');
			
			s[i] = bit;
		}

		dfs(0, 0, antic);
	}

	private static void dfs(int index, int depth, int bit) {
		if (depth == K-5) {
            int count = 0;
            int test = bit;
            for (int word : s) {
                int check = (test | word);
                if (check == test) {
                    count++;
                }
            }

			answer = Integer.max(answer, count);
			return;
		}

		for (int i = index; i < 26; i++) {
			// 기존 단어 배제
			if ((bit & (1 << i)) != 0) {
				continue;
			}
			dfs(i + 1, depth + 1, (bit | 1 << i));
		}

	}

}
/*
 * class Node implements Comparable<Node> { int number; double fail;
 * 
 * public Node(int number, double fail) { this.number = number; this.fail =
 * fail; }
 * 
 * @Override public int compareTo(Node o) { // TODO Auto-generated method stub
 * if(this.fail == o.fail) return Integer.compare(this.number, o.number); return
 * -Double.compare(this.fail, o.fail); } }
 */

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
