import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}
	
	private static void SetData() {
		InputReader in = new InputReader(System.in);

		int testcase = in.nextInt();
		sb = new StringBuilder();

		for (int t = 1; t <= testcase; t++) {
			int number = in.nextInt();
			
			solve(number, in);
		}
	}
	
	private static void solve(int number, InputReader in) {
		int[] rank = new int[number+1];
		for(int i = 1; i <= number; i++)
			rank[i] = in.nextInt();
		
		boolean[][] map = new boolean[number+1][number+1];
		int[] indegree = new int[number+1];
		for(int i = 1; i <= number; i++) {
			for(int j = 1; j < i; j++) {
				map[rank[i]][rank[j]] = true;
				indegree[rank[i]]++;
			}
		}
		
		int k = in.nextInt();
		for(int i = 0 ; i < k; i++) {
			int t1 = in.nextInt();
			int t2 = in.nextInt();
			
			if(map[t1][t2]) {
				indegree[t1]--;
				indegree[t2]++;
			} else {
				indegree[t1]++;
				indegree[t2]--;
			}
			
			map[t1][t2] = !map[t1][t2];
			map[t2][t1] = !map[t2][t1];
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i <= number; i++) {
			if(indegree[i] == 0)
				queue.add(i);
		}
		
		int loopCount = 0;
		String answer = "";
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			loopCount++;
			answer += temp + " ";
			
			for(int i = 1; i <= number; i++) {
				if(!map[i][temp]) continue;
				
				indegree[i]--;
				if(indegree[i] == 0)
					queue.add(i);
			}
			
		}
		
		if(loopCount == number)
			sb.append(answer).append("\n");
		else
			sb.append("IMPOSSIBLE").append("\n");
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