import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static boolean[] primes, check;
	static Queue<int[]> queue;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		sb = new StringBuilder();

		int testcase = in.nextInt();
		primes = new boolean[10000];
		check = new boolean[10000];
		getPrimeNumber();

		for (int t = 0; t < testcase; t++) {
			Arrays.fill(check, false);
			bfs(in.nextInt(), in.nextInt());
		}
	}

	private static void bfs(int preNumber, int postNumber) {
		queue = new LinkedList<>();
		queue.add(new int[] { preNumber, 0 });
		if (preNumber == postNumber) {
			sb.append(0).append("\n");
			return;
		}
		
		while (!queue.isEmpty()) {
			int[] primeRoot = queue.poll();
			if (check[primeRoot[0]])	continue;

			// 지나온건 다시 안돌아가도록 check
			check[primeRoot[0]] = true;

			char[] numbers = Integer.toString(primeRoot[0]).toCharArray();

			// 4자리 모두 0~9로 변경
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j < 4; j++) {
					// 맨 앞이 0이 되는 경우 제외
					if (j == 0 && i == 0)	continue;

					// 숫자 변경
					char temp = numbers[j];
					numbers[j] = (char) (i + '0');
					int changedPrimeNumber = Integer.parseInt(String.valueOf(numbers));
					numbers[j] = temp;

					// 바꾼 값이 이미 간적이 있으면
					if (check[changedPrimeNumber])		continue;
					
					if (!primes[changedPrimeNumber]) {
						if (changedPrimeNumber == postNumber) {
							sb.append(primeRoot[1] + 1).append("\n");
							return;
						}
						queue.add(new int[] { changedPrimeNumber, primeRoot[1] + 1 });
					}
				}
			}
		}
		
		sb.append("Impossible").append("\n");
	}

	private static void getPrimeNumber() {
		primes[0] = primes[1] = true;
		for (int i = 2; i < 10000; i++) {
			// true면 이미 소수이므로 pass
			if (primes[i]) {
				continue;
			}

			// 해당 수로 나누어 떨어지는 수는 소수이므로 true로 check
			for (int j = i + i; j < 10000; j += i) {
				primes[j] = true;
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