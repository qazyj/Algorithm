import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int N, answer;
	static StringBuilder sb;
	static int[][] array;
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		sb = new StringBuilder();		
		array = new int[N][N];	
        
		for(int i = 0; i < N; i++) {
			String s = in.nextLine();
			
			for(int j = 0; j < N; j++) {
				array[i][j] = s.charAt(j) - '0';
			}
		}

		dfs(N, 0, 0);
	}

	private static void dfs(int n, int i, int j) {
		// 압축 가능
		if(compression(n, i, j)) {
			sb.append(array[i][j]);
			return;
		}

		n = n / 2;	
		
		sb.append('(');	
		
		dfs(n, i, j);	// 왼위
		dfs(n, i, j + n);	// 오위
		dfs(n, i + n, j);	// 왼밑
		dfs(n ,i + n, j + n);	// 오밑
		
		sb.append(')');	
	}
	
	public static boolean compression(int n, int i, int j) {
		int value = array[i][j];
		
		for(int x = i; x < i + n; x++) {
			for(int y = j; y < j + n; y++) {
				if(value != array[x][y]) {
					return false;
				}
			}
		}
		return true;
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