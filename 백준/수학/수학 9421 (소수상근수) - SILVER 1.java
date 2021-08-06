import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static boolean[] prime;
	static int[] sangGeun;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		sb = new StringBuilder();
		prime = new boolean[1000001];
		sangGeun = new int[1000001];
		
		getPrimeNumber();
		
		for (int i = 1; i <= N; i++) {
			if (getSangGeunSoo(i) == 1 && !prime[i]) {
				sb.append(i).append("\n");
			}
		}
	}
	
	static int getSangGeunSoo(int index) {
		
		if(sangGeun[index] != 0) {
			return sangGeun[index];
		}
		
		int save=index, sum = 0;
		while(true) {
			int temp=save%10;
			sum+= Math.pow(temp, 2);
			save=save/10;
			if(save==0)
				break;					
		}
		
		// 상근수
		if(sum==1) {
			sangGeun[index]=1;
		}
		else { // 상근수 X
			sangGeun[index]=2;
			sangGeun[index]=getSangGeunSoo(sum);
		}
		
		return sangGeun[index];
	}
	
	// 소수 체크
	private static void getPrimeNumber() {
		prime[0] = prime[1] = true;
		for (int i = 2; i <= 1000000; i++) {
			if (prime[i]) {
				continue;
			}

			for (int j = i + i; j <= 1000000; j += i) {
				prime[j] = true;
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