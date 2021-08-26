import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N;
	static int[] array, index;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new int[N + 1];
		index = new int[N + 1];

		sb = new StringBuilder();

		ArrayList<Ball> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arrayList.add(new Ball(i+1, in.nextInt(), in.nextInt()));
		}
		Collections.sort(arrayList);
		
		int sum = 0;
		int temp = 0;
        for (int i = 0; i < N; i++) {
            Ball ball = arrayList.get(i);
            
            for(int j = temp; arrayList.get(j).size < ball.size; j++) {
            	Ball ballTemp = arrayList.get(j);
            	         	
                sum += ballTemp.size;
                index[ballTemp.color] += ballTemp.size;
                temp++;
            }

            array[ball.index] = sum - index[ball.color];
        }

		for (int i = 1; i <= N; i++) {
			sb.append(array[i]).append("\n");
		}
	}
}

class Ball implements Comparable<Ball> {
	int index;
	int color;
	int size;

	public Ball(int index, int color, int size) {
		this.index = index;
		this.color = color;
		this.size = size;
	}

	@Override
	public int compareTo(Ball ball) {
		 return this.size - ball.size;
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