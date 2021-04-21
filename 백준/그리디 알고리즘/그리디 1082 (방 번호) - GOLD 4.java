import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, price, answer;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		// System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		if(N == 1) {
			System.out.println("0");
			System.exit(0);
		}
		ArrayList<Number> number = new ArrayList<>();
		ArrayList<Index> index = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int value = in.nextInt();
			number.add(new Number(i, value));
			index.add(new Index(i, value));
		}
		Collections.sort(number);
		Collections.sort(index);
		
		price = in.nextInt();

		String temp = "";
		if(number.get(0).index != 0) {
			price -= number.get(0).value;
			temp = temp + number.get(0).index;
		} else {
			price -= number.get(1).value;
			temp = temp + number.get(1).index;
		}
		while (price >= number.get(0).value) {
			price -= number.get(0).value;
			temp = temp + number.get(0).index;
		}

		for (int i = 0; i < temp.length(); i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (i == 0 && number.get(0).index == 0) {
					if (number.get(1).value + price >= index.get(j).value) {
						price += number.get(1).value;
						temp = index.get(j).index + temp.substring(i+1, temp.length());
						price -= index.get(j).value;
						break;
					}
				} else {
					if (number.get(0).value + price >= index.get(j).value) {
						price += number.get(0).value;
						temp = temp.substring(0, i) + index.get(j).index + temp.substring(i+1, temp.length());
						price -= index.get(j).value;
						break;
					}
				}
			}
		}
		System.out.println(temp);
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