import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, answer, limit;
	static boolean[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(0, 0);
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new boolean[10];
		// 현재 길이보다 +1 해준 이유는 값이 9999이런식으로 가는데 9를 쓰지 못하는 경우 +1을 해주지않으면 8888에서 +로 9999를 만들어야 하는데
		// +1해주면 10000에서 -한번만 누르면 가능함.
		limit = (N+"").length() + 1;

		int numberOfBrokenRemoteControl = in.nextInt();
		for (int i = 0; i < numberOfBrokenRemoteControl; i++)
			array[in.nextInt()] = true;

		// 초기값 100에서 +,-만 사용해서채널 N을 틀 때 누르는 수
		answer = Math.abs(N - 100);
	}

	private static void dfs(int value, int count) {
		// basecase (현재 수가 N보다 높아지면 그만 돌린다.)
		if (limit < count)
			return;

		// 현재 누른 횟수와 +,-만 사용해서 채널 N을 틀 때 누르는 수
		answer = Math.min(answer, count + Math.abs(N - value));
		for (int i = 0; i < 10; i++) {
			if (array[i])
				continue;
			
			dfs((value * 10) + i, count + 1);
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