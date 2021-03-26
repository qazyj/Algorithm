import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, max, min;
	static int[] array, mathSymbol;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(max + "\n" + min);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N];
		mathSymbol = new int[4];
		check = new boolean[4];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++)
			array[i] = in.nextInt();
		
		int count = 0;
		for(int i = 0 ; i < 4; i++) {
			mathSymbol[i] = in.nextInt();
			count += mathSymbol[i];
		}
		
		dfs(1, count, array[0]);
	}
	
	private static void dfs(int index, int count, int value) {
		if(count == 0) {
			max = Math.max(max, value);
			min = Math.min(min, value);
		}
		
		for(int j = 0; j < 4; j++) {
			if(mathSymbol[j] == 0)		continue;
				
			mathSymbol[j]--;
			dfs(index + 1, count-1, calculate(value, array[index], j));
			mathSymbol[j]++;
		}		
	}
	
	private static int calculate(int a, int b, int symbol) {
		if(symbol == 0)
			return a + b;
		else if(symbol == 1)
			return a - b;
		else if(symbol == 2)
			return a * b;
		else
			return a / b;
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