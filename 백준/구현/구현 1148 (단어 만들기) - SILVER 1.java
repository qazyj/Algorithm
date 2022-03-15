import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();

		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		array = new int[200000][26];
		sb = new StringBuilder();

		int index = 0;
		while (true) {
			String s = in.nextLine();
			if (s.equals("-"))
				break;

			for (int i = 0; i < s.length(); i++) {
				array[index][s.charAt(i) - 'A']++;
			}
			index++;
		}

		while (true) {
			String s = in.nextLine();
			if (s.equals("#"))
				break;
			int[] save = new int[26];
			int[] temp = new int[26];
			for (int i = 0; i < s.length(); i++) {
				temp[s.charAt(i) - 'A']++;
			}

			case1: for (int k = 0; k < index; k++) {
				for (int z = 0; z < 26; z++) {
					if (temp[z] < array[k][z])
						continue case1;
				}
				for (int z = 0; z < 26; z++) {
					if (array[k][z] > 0)
						save[z]++;
				}
			}

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 26; i++) {
				if (save[i] != 0)
					min = Math.min(min, save[i]);
				if (save[i] == 0 && temp[i] > 0)
					min = 0;
				max = Math.max(max, save[i]);
			}

			StringBuffer minChar = new StringBuffer();
			StringBuffer maxChar = new StringBuffer();

			for (int i = 0; i < 26; i++) {
				if (min != 0 && min == save[i]) {
					minChar.append((char) ('A' + i));
				} else if (min == 0 && temp[i] > 0 && save[i] == 0)
					minChar.append((char) (i + 'A'));

				if (max == save[i] && temp[i] > 0) {
					maxChar.append((char) ('A' + i));
				}
			}

			sb.append(minChar + " " + min + " " + maxChar + " " + max).append("\n");

		}
	}
}

class Alpha implements Comparable<Alpha> {
	char alpha;
	int count;

	public Alpha(char alpha, int count) {
		this.alpha = alpha;
		this.count = count;
	}

	@Override
	public int compareTo(Alpha o) {
		// TODO Auto-generated method stub
		return this.count - o.count;
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