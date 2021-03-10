import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static int answer, count;
	static Set<Integer> check;
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		answer = Integer.MAX_VALUE;
		count = 0;
		check = new HashSet<Integer>();
		queue = new LinkedList<>();
		int temp = 0, input = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				input = in.nextInt();
				if (input == 0)
					input = 9;
				temp = temp * 10 + input;
			}
		}

		check.add(temp);
		queue.offer(new int[] {temp, 0});

		bfs();
		
		if(answer == Integer.MAX_VALUE) answer = -1;
	}

	private static void bfs() {
		int nowNumber[], nowIndex, nowRow, nowColumn, nextNumber;
		String nowString;
		while (!queue.isEmpty()) {
			nowNumber = queue.poll();
			if (nowNumber[0] == 123456789)
				answer = Math.min(answer, nowNumber[1]);

			nowString = String.valueOf(nowNumber[0]);
			nowIndex = nowString.indexOf('9');
			nowRow = nowIndex / 3;
			nowColumn = nowIndex % 3;

			for (int direction = 0; direction < 4; direction++) {
				int r = nowRow + x[direction];
				int c = nowColumn + y[direction];

				if (r < 0 || c < 0 || r >= 3 || c >= 3)			continue;

				int nextIndex = r * 3 + c;	// 이동할 index

				StringBuilder next = new StringBuilder(nowString);
				char temp = next.charAt(nextIndex);
				next.setCharAt(nextIndex, '9');
				next.setCharAt(nowIndex, temp);
				nextNumber = Integer.parseInt(next.toString());	//이동한 값
				
				if(check.contains(nextNumber)) continue;
				
				count++;
				check.add(nextNumber);
				queue.offer(new int[] {nextNumber, nowNumber[1] + 1});
				count--;
			}

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