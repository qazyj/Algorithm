import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int max, min;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(min + " " + max);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		String N = in.nextLine();
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		Recursion(N, 0);
	}
	
	private static void Recursion(String s, int count) {
		count += getCountOfDecimal(s);
		
		// baseacse
		if(s.length() == 1) {
			max = Math.max(max, count);
			min = Math.min(min, count);
			return;
		}
		
		if(s.length() == 2) {
			Recursion(Integer.toString(Integer.parseInt(s.substring(0,1)) + 
					Integer.parseInt(s.substring(1,2))), count);
			return;
		}
		
		for(int i = 1; i < s.length(); i++) {
			for(int j = i + 1; j < s.length(); j++) {
				Recursion(getSubString(s,i,j), count);
			}
		}
	}
	
	private static int getCountOfDecimal(String s) {
		int count = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != '0' && (s.charAt(i) - '0') % 2 != 0) {
				count++;
			}
		}
		return count;
	}
	
	private static String getSubString(String s, int a, int b) {
		return Integer.toString(Integer.parseInt(s.substring(0,a)) + 
				Integer.parseInt(s.substring(a,b)) + Integer.parseInt(s.substring(b,s.length())));
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