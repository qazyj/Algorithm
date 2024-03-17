import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		int n = in.nextInt();
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		for(int i = 0 ; i < n; i++) {
			int startTime = in.nextInt();
			int endTime = in.nextInt();

			pq.add(new Lecture(startTime, endTime));
		}

		int answer = 0;
		int cur = 0;
		PriorityQueue<Integer> end = new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Lecture lecture = pq.poll();

			end.add(lecture.endTime);
			while(!end.isEmpty() && end.peek() <= lecture.startTime) {
				end.poll();
			}

			cur = end.size();
			answer = Math.max(answer, cur);
		}
		System.out.println(answer);
	}
}

class Lecture implements Comparable<Lecture> {
	int startTime;
	int endTime;

	public Lecture(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}


	@Override
	public int compareTo(Lecture o) {
		return this.startTime - o.startTime;
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