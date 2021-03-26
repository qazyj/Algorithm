import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, S;
	static int[] number;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		Sort();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		number = new int[N];
		sb = new StringBuilder();
		
		for(int i = 0; i < N; i++)
			number[i] = in.nextInt();
		S = in.nextInt();
	}

	private static void Sort() {
		for(int i = 0; i < N; i++) {
			int x = number[i];
			int y = i;
			int temp;
			
			// j-i <= S 해주는 이유 (시간) - j부터 i까지 최대 S번 바꿀 수 있는데 j-i가 S보다 크면 못 바꿈.
			for(int j = i + 1; j < N && j-i <= S; j++) {
				if(x < number[j]) {
					x = number[j];
					y = j;
				}	
			}
			
			// 현재 j-i중에 가장 큰 값을 그 전의 값과 바꿔주는 작업
			for(S -= (y-i); y > i; y--) {
				temp = number[y];
				number[y] = number[y-1];
				number[y-1] = temp;
			}
			
			if(S <= 0) break;		// S 가 0이하이면 바꿀 수 있는게 없으니 반복문 나옴
		}

		for(int i = 0; i < N; i++)
			sb.append(number[i] + " ");
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