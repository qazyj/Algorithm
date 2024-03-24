import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		Flower[] flowers = new Flower[n];
		for(int i = 0; i < n; i++) {
			int startMonth = in.nextInt();
			int startDay = in.nextInt();
			int endMonth = in.nextInt();
			int endDay = in.nextInt();
			flowers[i] = new Flower(startMonth*100+startDay, endMonth*100+endDay);
		}
		Arrays.sort(flowers);

		int start = 301;
		int end = 1201;
		int max = 0;
		int count = 0;
		int index = 0;
		while(start < end) {
			boolean isUsed = false;

			for(int i = index; i < n; i++) {
				if(flowers[i].startDay > start) continue;

				if(max < flowers[i].endDay) {
					index = i;
					max = flowers[i].endDay;
					isUsed = true;
				}
			}

			if(isUsed) {
				start = max;
				count += 1;
			} else {
				break;
			}
		}

		if(max < end) {
			System.out.println(0);
		} else {
			System.out.println(count);
		}
	}
}

class Flower implements Comparable<Flower> {
	int startDay;
	int endDay;

	public Flower(int startDay, int endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}


	@Override
	public int compareTo(Flower o) {
		if(this.startDay != o.startDay) {
			return this.startDay - o.startDay;
		} else {
			return o.endDay - this.endDay;
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