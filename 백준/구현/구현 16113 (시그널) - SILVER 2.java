import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static char[][] map;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();
		int leng = in.nextInt();

		String str = in.nextLine();

		map = new char[5][leng / 5];

		for (int i = 0; i < 5; i++) {
			map[i] = str.substring(i * leng / 5, leng / 5 * (i + 1)).toCharArray();
		}

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < leng / 5; i++) {
			if (map[0][i] == '#') {

				if (i + 2 <= leng / 5) {
					if (map[0][i + 1] == '#' && map[0][i + 2] == '#') {

						list.add(choice(i));

						i = i + 3;

						if (i >= leng / 5)
							break;

						continue;
					}
				}

				if (map[3][i] == '#')
					list.add(1);
				else {
					list.add(4);
					i = i + 3;
					if (i >= leng / 5)
						break;
				}

			}

		}

		for (int n : list) {
			sb.append(n);
		}
	}

	private static int choice(int x) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(map[i][j + x]);
			}
		}

		return check(sb.toString());
	}

	private static int check(String sb) {

		int result = 10;
		String[] num = new String[10];

		num[0] = "####.##.##.####";
		num[2] = "###..#####..###";
		num[3] = "###..####..####";
		num[5] = "####..###..####";
		num[6] = "####..####.####";
		num[7] = "###..#..#..#..#";
		num[8] = "####.#####.####";
		num[9] = "####.####..####";

		for (int i = 0; i < num.length; i++) {
			if (sb.equals(num[i])) {
				result = i;
				break;
			}
		}
		return result;
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