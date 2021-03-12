import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static int N, M, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		M = in.nextInt();
		char[][] array = new char[N][M];
		answer = Integer.MAX_VALUE;
		int bX = 0, bY = 0, rX = 0, rY = 0;
		
		String s;
		for(int i = 0; i < N; i++) {
			s = in.nextLine();
			for(int j = 0 ; j < M; j++) {
				array[i][j] = s.charAt(j);
				if(array[i][j] == 'B') {
					bX = i;
					bY = j;
				}
				if(array[i][j] == 'R') {
					rX = i;
					rY = j;
				}
			}
		}
		
		for(int i = 0; i < 4; i++) {
			bfs(array, i, 0, bX, bY, rX, rY);
		}
		
		if(answer == Integer.MAX_VALUE) answer = -1;
	}
	
	private static void bfs(char[][] array,int index, int count, int bX, int bY, int rX, int rY) {
		if(count > 9) {
			return;
		}
		int blueX = bX;
		int blueY = bY;
		int redX = rX;
		int redY = rY;
		/*
		System.out.println("\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}*/
			
		
		boolean check = false, check2 = false;
		while(true) {
			// move
			blueX += x[index];
			blueY += y[index];
			redX += x[index];
			redY += y[index];
			
			// 움직일 수 없는 경우
			if((array[blueX][blueY] == '#' || (blueX + x[index] == redX && blueY + y[index] == redY)) &&
					(array[redX][redY] == '#' || (redX + x[index] == blueX && redY + y[index] == blueY))) {
				blueX -= x[index];
				blueY -= y[index];
				redX -= x[index];
				redY -= y[index];
				if(array[blueX+x[index]][blueY+y[index]] == 'O')
					check = true;
				break;
			}
			
			// 앞이 막혀있는 경우
			if(array[blueX][blueY] == '#') {
				blueX -= x[index];
				blueY -= y[index];
			}
			
			if(array[redX][redY] == '#') {
				redX -= x[index];
				redY -= y[index];
			}
			
			// 골인
			if(array[redX][redY] == 'O') {
				check2 = true; 
			}
			
			// 잘못된 골인
			if(array[blueX][blueY] == 'O') {
				check = true;
				break;				
			}
			
		}
		
		if(!check && check2) {
			answer = Math.min(answer, count+1);
			return;
		}
		
		if(!check) {
			char temp = array[blueX][blueY];
			array[blueX][blueY] = array[bX][bY];
			array[bX][bY] = temp;
			temp = array[redX][redY];
			array[redX][redY] = array[rX][rY];
			array[rX][rY] = temp;
			count++;
		} else {
			blueX = bX;
			blueY = bY;
			redX = rX;
			redY = rY;
		}
		
		for(int i = 0; i < 4; i++) {
			if(i == index) continue;
			
			bfs(array, i, count, blueX, blueY, redX, redY);
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