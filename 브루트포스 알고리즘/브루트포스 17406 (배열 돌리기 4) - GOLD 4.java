import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int N, M, K, answer;
	static int[][] array, tempArray, rcs;
	static boolean[] check;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		RotateArray();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		array = new int[N][M];
		tempArray = new int[N][M];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();
				tempArray[i][j] = array[i][j];
			}
		}

		rcs = new int[K][3];
		for (int i = 0; i < K; i++) {
			rcs[i][0] = in.nextInt() - 1;
			rcs[i][1] = in.nextInt() - 1;
			rcs[i][2] = in.nextInt();
		}
	}

	public static void RotateArray() {
		int[] order = new int[K];
		for(int i = 0; i < K; i++)
			order[i] = i;
		
		while(true) {
			// 여기서 최대값 찾기 ( 회전 연산 ) 
			for (int n : order) {// 회전 순서
				int r = rcs[n][0], c=rcs[n][1], s = rcs[n][2];
				while(s>0) {
					int a = array[r-s][c-s];
					for(int i = r-s; i+1<=r+s;i++) {
						array[i][c-s] = array[i+1][c-s];
					}
					for(int i = c-s;i+1<=c+s;i++) {
						array[r+s][i] = array[r+s][i+1];
					}
					for(int i = r+s;i-1>=r-s;i--) {
						array[i][c+s] = array[i-1][c+s];
					}
					for(int i = c+s; i-1>=c-s;i--) {
						array[r-s][i] = array[r-s][i-1];
					}
					array[r-s][c-s+1] = a;
					if(--s==0)break;
				}
				
			}
			
			// 최소 행 찾기
			for(int i = 0; i<N;i++) {
				int temp = 0; 
				for (int j = 0; j < M; j++) {
					temp+=array[i][j];
				}
				answer = temp<answer?temp:answer;
			}
			
			// 원래 배열로 back
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					array[i][j] = tempArray[i][j];
				}
			}
			
			// 순서 바굼
			int f = K-2;
			for (; f>=0  ; f--) {
				if(order[f]<order[f+1])break;
			}
			if(f==-1)break;			
			int l = K-1;
			for(;l>f;l--) {
				if(order[f]<order[l])break;
			}			
			int temp = order[f];
			order[f]= order[l];
			order[l] = temp;
			++f;
			for(int i = K-1;i>f;i--,f++) {
				temp = order[i];
				order[i]= order[f];
				order[f] = temp;
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