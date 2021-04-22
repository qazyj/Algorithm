import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Algorithm {
	static int[] parent, rank;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();
		
        while (testcase-- > 0) {
            int F = in.nextInt();
 
            parent = new int[F * 2];
            rank = new int[F * 2];
            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
 
            int friend = 0;
            HashMap<String, Integer> map = new HashMap<>();
 
            for (int i = 0; i < F; i++) {
            	String[] s = in.nextLine().split(" ");
                String f1 = s[0];
                String f2 = s[1];
 
                if (!map.containsKey(f1)) {
                    map.put(f1, friend++);
                }
 
                if (!map.containsKey(f2)) {
                    map.put(f2, friend++);
                }
 
                sb.append(union(map.get(f1), map.get(f2))).append("\n");
            }
        }
	}
	
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
 
        return parent[x] = find(parent[x]);
    }
 
    public static int union(int x, int y) {
        x = find(x);
        y = find(y);
 
        if (x != y) {
            parent[y] = x;
            rank[x] += rank[y]; 

            rank[y] = 1;
        }
 
        return rank[x];
    }
}

class Node {
	int index, count;

	Node(int index, int count) {
		this.index = index;
		this.count = count;
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