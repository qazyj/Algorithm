import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Main {
	static int testcase, N, M;
	static int[][] book;
	static boolean[] check;
	static StringBuilder sb;	

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		testcase = in.nextInt();
		sb = new StringBuilder();
		
		for(int i = 0; i < testcase; i++) {
			N = in.nextInt();
			M = in.nextInt();
			
			check = new boolean[N];
			book = new int[M][2];
			for(int j = 0; j < M; j++) {
				book[j][0] = in.nextInt();
				book[j][1] = in.nextInt();
			}
			
	        Arrays.sort(book, new Comparator<int[]>() {
	            @Override
	            public int compare(int[] o1, int[] o2) {
	                if(o1[1] != o2[1])	// b 가 같지 않은 경우 b로 오름차순 정렬
	                	return Integer.compare(o1[1], o2[1]);
	                else		// b가 같은 경우 a로 오름차순 정렬
	                	return Integer.compare(o1[0], o2[0]);
	            }
	        });
		
			
			FindMaxValue(); // 각 testcase마다 책을 줄 수 있는 학생의 최대 수를 찾는다.
		}
	}

	private static void FindMaxValue() {
		int count = 0; // 나누어주는 학생의 수를 count
		
		for(int i = 0; i < M; i++) {
			int a = book[i][0];
			int b = book[i][1];

			// 해당하는 범위 내에서
			// 가능한 가장 작은 번호의 책부터 뽑는다.
			for (int j = a-1; j < b; j++) {
				if (!check[j]) {
					check[j] = true;
					count++;
					break;
				}
			}
		}		
		
		sb.append(count + "\n");
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