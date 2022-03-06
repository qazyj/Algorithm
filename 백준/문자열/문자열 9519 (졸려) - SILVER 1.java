import java.io.*;
import java.util.*;

public class Main {
	static int X;
	static String word, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		X = in.nextInt();
		int temp = X;
		word = in.nextLine();
		int N = word.length();
		String tempString = word;
		StringBuilder head;
		StringBuilder tail;
		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add(word);
		int count = 0;
		while(temp>0) {
			head = new StringBuilder();
			tail = new StringBuilder();
			if(N%2 == 0) {
				for(int i=N-1; i>=0; i-=2) {
					tail.append(tempString.charAt(i));
				}
				for(int i=0; i<N; i+=2) {
					head.append(tempString.charAt(i));
				}
			}else {
				for(int i=N-2; i>=0; i-=2) {
					tail.append(tempString.charAt(i));
				}
				for(int i=0; i<N; i+=2) {
					head.append(tempString.charAt(i));
				}
			}

			head.append(tail);
			tempString = head.toString();
			list.add(tempString);
			if(map.get(tempString) == null) {
				map.put(tempString, count++);
			}else {
				break;
			}
			temp--;
		}

		if(temp>0) {
			temp = X % count;
			answer = list.get(temp);
		}else {
			answer = tempString;
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