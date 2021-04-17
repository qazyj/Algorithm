import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algorithm {
	static int N, L, R, answer;
	static int[][] array;
	static boolean[][] check;
	static Queue<int []> queue;
	static ArrayList<int []> union;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1}; 
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		L = in.nextInt();
		R = in.nextInt();
		array = new int[N][N];
		queue = new LinkedList<>();
		union = new ArrayList<int []>();
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				array[i][j] = in.nextInt();
			}
		}
		
		MovePopulation();
	}
	
	private static void MovePopulation() {
		while(PossibleMovePopulition()) {
			answer++;
		}
	}
	
	private static boolean PossibleMovePopulition() {
		boolean possible = false;
		check = new boolean[N][N];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(!check[i][j] && FindUnion(i,j)) {
					possible = true;
				}
			}
		}		
		return possible;
	}
	
	private static boolean FindUnion(int i, int j) {
		boolean possible = false;
		queue.add(new int[] {i,j});
		check[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] location = queue.poll();
			
			for(int direction = 0; direction < 4; direction++) {
				int r = location[0] + dx[direction];
				int c = location[1] + dy[direction];
				
				if(r < 0 || c < 0 || r >= N || c >= N || check[r][c]) continue;
				int gap = Math.abs(array[location[0]][location[1]] - array[r][c]);
				
				if(L <= gap && gap <= R) {
					union.add(new int[] {r,c});
					check[r][c] = true;
					queue.add(new int[] {r,c});
				}
			}
		}
		
		if(union.size() >= 1) {
			union.add(new int[] {i,j});
			SharePopulation();
			possible = true;
		}
		
		return possible;
	}
	
	private static void SharePopulation() {
		int sum = 0;
		for(int index = 0; index < union.size(); index++) {
			sum += array[union.get(index)[0]][union.get(index)[1]];
		}
		for(int index = 0; index < union.size(); index++) {
			array[union.get(index)[0]][union.get(index)[1]] = sum / union.size();
		}
		union.clear();
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