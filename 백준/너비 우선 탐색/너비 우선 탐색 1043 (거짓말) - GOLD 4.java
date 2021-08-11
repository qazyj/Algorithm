import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M, answer;
	static ArrayList<Integer>[] array;
	static boolean[] check, partyCheck;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		answer = M;
		check = new boolean[N + 1];
		partyCheck = new boolean[M];
		array = new ArrayList[M];

		int truesNumber = in.nextInt();
		for (int i = 0; i < truesNumber; i++) {
			check[in.nextInt()] = true;
		}

		for (int i = 0; i < M; i++) {
			array[i] = new ArrayList<Integer>();
			int size = in.nextInt();
			for (int j = 0; j < size; j++) {
				array[i].add(in.nextInt());
			}
		}

		SaveAnswer();
	}

	public static void SaveAnswer() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i <= N; i++) {
			if (check[i])	queue.add(i);
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 0; i < M; i++) {
				if (partyCheck[i] || !array[i].contains(now))		continue;

				partyCheck[i] = true;
				answer--;				
				for (int j = 0; j < array[i].size(); j++) {
					int next = array[i].get(j);

					if (check[next]) continue;
					
					check[next] = true;
					queue.offer(next);					
				}				
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