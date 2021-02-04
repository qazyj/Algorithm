import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int[] array, check;
	static int n, x, y,count;

	public static void main(String[] args) throws Exception {
		SetData();
		BackTracking(1);
		System.out.println(count);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		n = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		count = 0;
		
		array = new int[25];
		check = new int[25];
		array[x] = array[y] = y - x - 1;
		check[y-x-1] = 1;
	}

	private static void BackTracking(int index) {
		if(index == 2*n){
			count++; return;
		}
		if(array[index]==0){
			for(int i=1; i<=n; i++){
				if(check[i] == 1) continue;
				if(index+i+1<=2*n && array[index+i+1] == 0){
					array[index] = array[index+i+1] = i;
					check[i] = 1;
					BackTracking(index+1);
					array[index] = array[index+i+1] = check[i] = 0;
				}
			}
		}
		else
			BackTracking(index+1);
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