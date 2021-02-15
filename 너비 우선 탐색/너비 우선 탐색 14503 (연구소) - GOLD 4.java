import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] array;
	static int N, M, answer;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		SetData();	
		blockArea();
		System.out.println(answer);
	}

	// µ•¿Ã≈Õ
	private static void SetData() throws Exception  {
		InputReader in = new InputReader(System.in);
		
		N = in.readInt();
		M = in.readInt();		
		array = new int[N][M];
		answer = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				array[i][j] = in.readInt();			
		}
	}
	
	private static void blockArea() {
		int totalArea = N*M;
		
		for (int first = 0; first < totalArea-2; first++) {
			if (array[first/M][first%M]==0) {
				array[first/M][first%M] = 1;
			}else {	continue;}
			
			for (int second = first+1; second < totalArea-1; second++) {
				if (array[second/M][second%M]==0) {
					array[second/M][second%M] = 1;
				}else {	continue;}
				
				for (int third = second+1; third < totalArea; third++) {
					if (array[third/M][third%M]== 0) {
						array[third/M][third%M] = 1;
					}else {	continue;}
					
					updateAnswer();
					
					array[third/M][third%M] = 0;
				}
				array[second/M][second%M] = 0;
			}
			array[first/M][first%M] = 0;
		}
	}
	
	private static void updateAnswer() {
		int result=0;
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if(array[x][y] == 2) {
					DFS(x,y);
				}
			}
		}

		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (array[x][y]==0) {
					result++;
				}else if (array[x][y] == 3) {
					array[x][y]=0;
				}
			}
		}

		answer = Math.max(answer, result);		
	}
	
	private static void DFS(int a, int b) {
		int r,c;
		for (int i = 0; i < 4; i++) {
			r = a+x[i];
			c = b+y[i];
			
			if(r < 0 || c < 0 || r >= N || c >= M || array[r][c] != 0) continue;

			array[r][c]=3;
			DFS(r,c);
			
		}
	}
    
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
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
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}