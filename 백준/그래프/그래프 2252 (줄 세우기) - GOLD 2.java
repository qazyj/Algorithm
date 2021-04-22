import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int N, M;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		array = new ArrayList<ArrayList<Integer>>();
		indegree = new int[N+1];
		sb = new StringBuilder();
		
		for(int i = 0; i <= N; i++) {
			array.add(new ArrayList<Integer>());
		}
		
		for(int i = 1 ; i <= M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			array.get(a).add(b);
			indegree[b]++;
		}
		
		solve();
	}
	
	private static void solve() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1 ; i <= N; i++) {
			if(indegree[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			int index = queue.poll();
			sb.append(index + " ");
			
			for(Integer temp : array.get(index)) {
                indegree[temp]--;
                
                if(indegree[temp] == 0)
                    queue.add(temp);
            }
		}
	}
}

class Number implements Comparable<Number> {
	int index;
	int value;

	public Number(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	@Override
	public int compareTo(Number o) {
		// TODO Auto-generated method stub
			return Integer.compare(this.value, o.value);
	}

}

class Index implements Comparable<Index> {
	int index;
	int value;

	public Index(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	@Override
	public int compareTo(Index o) {
		// TODO Auto-generated method stub
			return Integer.compare(this.index, o.index);
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