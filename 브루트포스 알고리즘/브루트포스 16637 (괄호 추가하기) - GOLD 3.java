import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
	static int N, answer;
	static int[] number;
	static char[] operator;

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(0, number[0]);
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = Integer.MIN_VALUE;
		number = new int[N / 2 + 1];
		operator = new char[N / 2];

		String temp = in.nextLine();
		int indexOfNumber = 0;
		int indexOfChar = 0;
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				number[indexOfNumber++] = Integer.parseInt(temp.charAt(i) + "");
			} else {
				operator[indexOfChar++] = temp.charAt(i);
				;
			}
		}
	}

	public static void dfs(int operatorIndex, int sum) {
		// basecase
		if (operatorIndex >= N / 2) {
			answer = Math.max(answer, sum);
			return;
		}

		// 괄호 안치고 진행하기
		int nobracket = Calculator(operatorIndex, sum, number[operatorIndex + 1]);
		dfs(operatorIndex + 1, nobracket);

		// 더이상 할 작업이 없으면 return
		if (operatorIndex + 1 >= N / 2)
			return;

		// 괄호 치고 진행하기
		int bracket = Calculator(operatorIndex + 1, number[operatorIndex + 1], number[operatorIndex + 2]);
		int result = Calculator(operatorIndex, sum, bracket);
		dfs(operatorIndex + 2, result);

	}

	public static int Calculator(int operatorIndex, int a, int b) {
		switch (operator[operatorIndex]) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 1;
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