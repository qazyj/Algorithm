import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static boolean[] primes;
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
		
		for (int i = 0; i < testcase; i++) {
			Arrays.fill(primes, false);
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
			if (!primes[primeRoot[0]]) {
				// 지나온건 다시 안돌아가도록
				primes[primeRoot[0]] = true;
				
				char[] numbers = Integer.toString(primeRoot[0]).toCharArray();

				for (int i = 0; i <= 9; i++) {
					for (int j = 0; j < 4; j++) {
						if (j == 0 && i == 0)   continue;
						
						char temp = numbers[j];
						numbers[j] = (char) (i + '0');
						int changedPrimeNumber = Integer.parseInt(String.valueOf(numbers));
						numbers[j] = temp;
						if (primes[changedPrimeNumber])		continue;
						if (check(changedPrimeNumber)) {
							if (changedPrimeNumber == postNumber) {
								sb.append(primeRoot[1] + 1).append("\n");
								return;
							}
							queue.add(new int[] { changedPrimeNumber, primeRoot[1] + 1 });
						}
					}
				}
			}
		}
		sb.append("Impossible").append("\n");
	}

	private static boolean check(int number) {
		for (int i = 2; i <= (int) Math.sqrt(number); i++) {
			if (number % i == 0)
				return false;
		}
		return true;
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