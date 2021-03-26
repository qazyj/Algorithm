import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int[] x =  { -1, 0, 1, 0 };
    static int[] y = { 0, 1, 0, -1 };
	static int[][] array;
	static int N, L, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		L = in.nextInt();
		array = new int[N][N];
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
			}
		}
		
		Calculate();
	}
	
	private static void Calculate() {
		// 가로
		for(int i = 0; i < N; i++) {
			// ->
			boolean check = false;		// 중간에 안되면 반복문을 중단시킬 수단
			boolean[] visit = new boolean[N];	// 겹치는 경우를 방지
			int pre = array[i][0];		// 이전 값
			int count = 0;
			for(int j = 1; j < N; j++) {
				// 이전 값보다 -2가 작으면 바로 종료
				if(pre - 1 > array[i][j]) 	{
					check = true;
					break;
				}
				
				// 이전 값 -1이면 count시작
				if(pre - 1 == array[i][j]) {
					visit[j] = true;		// 겹치는 경우 방지 (>> 갔다가 << 작업해주는데 이 경우 방지)
					count++;
				}
				
				// 이미 -1칸이 1개 나왔는데 pre값보다 큰값이 나오면 반복문 빠져나옴( L개수가 나오지 않았음)
				if(count >= 1 && pre <= array[i][j]) {
					check = true;
					break;
				}
				
				// L개수만큼 나오면 count와 pre값 초기화
				if(count >= L) {
					count = 0;
					pre = array[i][j];
				}
				
				// pre보다 큰값이 나오면 초기화
				if(pre < array[i][j]) pre = array[i][j];
			}
			if(check || (count != 0 && count < L))
				continue;
			
			// <-
			pre = array[i][N-1];
			count = 0;
			for(int j = N -2; j >= 0; j--) {
				if(pre - 1 > array[i][j]) 	{
					check = true;
					break;
				}
				
				if(pre - 1 == array[i][j]) {
					count++;
					if(visit[j]) {
						check = true;
						break;
					}
						
				}
				
				if(count >= 1 && pre <= array[i][j]) {
					check = true;
					break;
				}
				
				if(count >= L) {
					count = 0;
					pre = array[i][j];
				}
				if(pre < array[i][j]) pre = array[i][j];
			}
			if(check || (count != 0 && count < L))
				continue;
			
			answer++;
		}
		
		// 세로
		for(int i = 0; i < N; i++) {
			// 위
			boolean check = false;
			boolean[] visit = new boolean[N];
			int pre = array[0][i];
			int count = 0;
			for(int j = 1; j < N; j++) {
				if(pre - 1 > array[j][i]) 	{
					check = true;
					break;
				}
				
				if(pre - 1 == array[j][i]) {
					count++;
					visit[j] = true;
				}
				
				if(count >= 1 && pre <= array[j][i]) {
					check = true;
					break;
				}
				
				if(count >= L) {
					count = 0;
					pre = array[j][i];
				}
				if(pre < array[j][i]) pre = array[j][i];
			}
			if(check || (count != 0 && count < L))
				continue;
			
			// 밑
			pre = array[N-1][i];
			count = 0;
			for(int j = N -2; j >= 0; j--) {
				if(pre - 1 > array[j][i]) 	{
					check = true;
					break;
				}
				
				if(pre - 1 == array[j][i]) {
					count++;
					if(visit[j]) {
						check = true;
						break;
					}
						
				}
				
				if(count >= 1 && pre <= array[j][i]) {
					check = true;
					break;
				}
				
				if(count >= L) {
					count = 0;
					pre = array[j][i];
				}
				if(pre < array[j][i]) pre = array[j][i];
			}
			if(check || (count != 0 && count < L))
				continue;
			
			answer++;
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