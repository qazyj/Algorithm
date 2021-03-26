import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int M, N, answer, array [][];

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		int M = in.nextInt();
		array = new int[M+6][N+6];
		for(int i = 3; i<N+3; i++){
			for(int j = 3; j<M+3; j++){
				array[j][i] = in.nextInt();
			}
		}
		
		for(int i=0; i<M+3; i++){
			for(int j=0; j<N+3; j++){
				dfs(i, j);
			}
		}
	}
	
	private static void dfs(int a, int b){		
		// 직선 (세로 놓기)
		int sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a+2][b];
		sum += array[a+3][b];
		if(answer<sum){
			answer = sum;
		}
		
		// 직선 (가로 놓기)
		sum=0;
		sum += array[a][b];
		sum += array[a][b+1];
		sum += array[a][b+2];
		sum += array[a][b+3];
		if(answer<sum){
			answer = sum;
		} 
		
		// 네모
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a+1][b+1];
		sum += array[a][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㄴ  // 왼상단시작 오른 하단 종료. (반시계방향)
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a+2][b];
		sum += array[a+2][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㄴ case // 왼상단시작 오른 하단 종료. (반시계방향)의 대칭.
		sum=0;
		sum += array[a][b+1];
		sum += array[a+1][b+1];
		sum += array[a+2][b+1];
		sum += array[a+2][b];
		if(answer<sum){
			answer = sum;
		}

		// ㄴ  // 왼하단시작 오른 상단 종료.
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a][b+1];
		sum += array[a][b+2];
		if(answer<sum){
			answer = sum;
		}
		// ㄴ  // 왼하단시작 오른 상단 종료.
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b+2];
		sum += array[a][b+1];
		sum += array[a][b+2];
		if(answer<sum){
			answer = sum;
		}


		// ㄴ  // 왼상단시작 오른 하단 종료 (시계방향) 
		sum=0;
		sum += array[a][b];
		sum += array[a][b+1];
		sum += array[a+1][b+1];
		sum += array[a+2][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㄴ  // 왼상단시작 오른 하단 종료 (시계방향) 의 대칭
		sum=0;
		sum += array[a][b];
		sum += array[a][b+1];
		sum += array[a+1][b];
		sum += array[a+2][b];
		if(answer<sum){
			answer = sum;
		}
		//  ㄴ  // 오른 상단시작  왼 하단 종료
		sum=0;
		sum += array[a][b+2];
		sum += array[a+1][b+2];
		sum += array[a+1][b+1];
		sum += array[a+1][b];
		if(answer<sum){
			answer = sum;
		}
		// ㄴ  // 오른 상단시작  왼 하단 종료 의 대칭  
		sum=0;
		sum += array[a+1][b+2];
		sum += array[a+1][b+1];
		sum += array[a+1][b];
		sum += array[a][b];
		if(answer<sum){
			answer = sum;
		}
		
		// ㄴㄱ  // 왼상단시작 오른 하단 종료
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a+1][b+1];
		sum += array[a+2][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㄴㄱ  // 오른 상단시작  하단 종료. 
		sum=0;
		sum += array[a][b+2];
		sum += array[a][b+1];
		sum += array[a+1][b+1];
		sum += array[a+1][b];
		if(answer<sum){
			answer = sum;
		}
		// ㄴㄱ  // 왼하단시작 오른 상단 종료. 
		sum=0;
		sum += array[a+2][b];
		sum += array[a+1][b];
		sum += array[a+1][b+1];
		sum += array[a][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㄴㄱ  // 왼상단시작 오른 하단 종료.  (ㄱㄴ꼴)
		sum=0;
		sum += array[a][b];
		sum += array[a][b+1];
		sum += array[a+1][b+1];
		sum += array[a+1][b+2];
		if(answer<sum){
			answer = sum;
		}
		// ㅗ   //  ㅜ
		sum=0;
		sum += array[a][b];
		sum += array[a][b+1];
		sum += array[a][b+2];
		sum += array[a+1][b+1];
		if(answer<sum){
			answer = sum;
		}
		// ㅗ   // ㅓ  
		sum=0;
		sum += array[a][b+1];
		sum += array[a+1][b+1];
		sum += array[a+2][b+1];
		sum += array[a+1][b];
		if(answer<sum){
			answer = sum;
		}
		// ㅗ   //  ㅗ  
		sum=0;
		sum += array[a+1][b];
		sum += array[a+1][b+1];
		sum += array[a+1][b+2];
		sum += array[a][b+1];
		if(answer<sum){
			answer = sum;
		}	
		// ㅗ   //  ㅏ  
		sum=0;
		sum += array[a][b];
		sum += array[a+1][b];
		sum += array[a+2][b];
		sum += array[a+1][b+1];
		if(answer<sum){
			answer = sum;
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