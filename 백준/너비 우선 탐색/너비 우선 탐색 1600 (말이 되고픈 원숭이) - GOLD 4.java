import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int K, W, H, answer;
	static int[][] array;
	static boolean[][][] check;
	static int[] x = {1,-1, 0, 0};
	static int[] y = {0, 0, 1, -1}; 
	static int[] hx = {-2, -2, 2, 2, 1, -1, 1, -1};
	static int[] hy = {1, -1, 1, -1, 2, 2, -2, -2};

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		K = in.nextInt();
		W = in.nextInt();
		H = in.nextInt();
		answer = Integer.MAX_VALUE;
		array = new int[H][W];
		check = new boolean[K+1][H][W];
		
		for(int i = 0 ; i < H; i++) {
			for(int j = 0; j < W; j++)
				array[i][j] = in.nextInt();
		}
		
		check[0][0][0] = true;
		bfs();
	}
	
	private static void bfs() {
		Queue<Monkey> queue = new LinkedList<Monkey>();
		queue.add(new Monkey(0,0,0,0));
		
		while(!queue.isEmpty()) {
			Monkey monkey = queue.poll();
			
			if(monkey.x == H-1 && monkey.y == W-1) {
				answer = Math.min(answer, monkey.count);
				return;
			}
			
			for(int direction = 0; direction < 4; direction++) {
				int r = monkey.x + x[direction];
				int c = monkey.y + y[direction];
				
				if(r < 0 || r >= H || c < 0 || c >= W) continue;
				if(check[monkey.kcount][r][c] || array[r][c] == 1) continue;
				
				check[monkey.kcount][r][c] = true;
				queue.add(new Monkey(r, c, monkey.count+1, monkey.kcount));
			}
			
			if(monkey.kcount == K) continue;
			for(int direction = 0; direction < 8; direction++) {
				int r = monkey.x + hx[direction];
				int c = monkey.y + hy[direction];
				
				if(r < 0 || r >= H || c < 0 || c >= W) continue;
				if(check[monkey.kcount+1][r][c] || array[r][c] == 1) continue;
				
				check[monkey.kcount+1][r][c] = true;
				queue.add(new Monkey(r, c, monkey.count+1, monkey.kcount+1));
			}
				
			
		}
		
		answer = -1;
	}
}

class Monkey{
	int x;
	int y;
	int count;
	int kcount;
	
	public Monkey(int x, int y, int count, int kcount){
		this.x = x;
		this.y = y;
		this.count = count;
		this.kcount = kcount;
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
