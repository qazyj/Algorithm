import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		int testcase = in.nextInt();

		for(int i = 1 ; i <= testcase; i++) {
			sb.append("Data set "+i+": ");
			String[] temp = in.nextLine().split(" ");
			check = new boolean[temp[1].length()+1][temp[2].length()+1];

			boolean check = CanMakeString(temp[0], temp[1], temp[2]);

			if(check)
				sb.append("yes").append("\n");
			else
				sb.append("no").append("\n");
		}

	}

	private static boolean CanMakeString(String first, String second, String third) {
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int[] {0, 0});
		check[0][0] = true;
		while (!queue.isEmpty()) {
			int[] index = queue.poll();

			if (index[0] + index[1] == third.length()) {
				return true;
			}

			if (index[0] < first.length()
					&& first.charAt(index[0]) == third.charAt(index[0] + index[1])
					&& !check[index[0] + 1][index[1]]) {
				queue.add(new int[]{ index[0] + 1, index[1] });
				check[index[0] + 1][index[1]] = true;
			}
			if (index[1] < second.length()
					&& second.charAt(index[1]) == third.charAt(index[0] + index[1])
					&& !check[index[0]][index[1] + 1]) {
				queue.add(new int[]{ index[0],index[1] + 1 });
				check[index[0]][index[1] + 1] = true;
			}
		}
		return false;
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