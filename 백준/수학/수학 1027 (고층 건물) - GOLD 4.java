import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int answer, number;
	static long[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		Calculate();
		System.out.println(answer);
	}
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		number = in.nextInt();
		array = new long[number+1];
		answer = 0;
		
		for(int index = 1; index <= number; index++)
			array[index] = in.nextLong();
		
	}
	
	private static void Calculate() {
		for(int i = 1;i <= number;i++){
			int temp=0;
			
			double angle = 99999999999L;
			for(int j = i-1;j >= 1;j--){
				double lean=(double)(array[j]-array[i])/(double)(j-i);
				if(lean<angle){
					temp++;
					angle=lean;
				}
			}
			
			angle = -99999999999L;
			for(int j = i+1;j <= number;j++){
				double lean=(double)(array[j]-array[i])/(double)(j-i);			
				if(lean>angle){
					temp++;
					angle=lean;
				}
			}
			
			answer=Math.max(answer,temp);
		}
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