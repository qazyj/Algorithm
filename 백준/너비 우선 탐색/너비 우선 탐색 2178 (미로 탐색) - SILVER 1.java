import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    static int[][] array;
    static boolean[][] check;
    static int N,M;
    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		SetData();
        bfs();
        System.out.println(array[N-1][M-1]);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
        N = in.nextInt();
        M = in.nextInt();
        
        array = new int[N][M];
        check = new boolean[N][M];
        check[0][0] = true;

        for (int i = 0; i < N; i++) {
            String s = in.readString();
            for (int j = 0; j < M; j++) {
                array[i][j] = s.charAt(j) - '0';
            }
        }		
	}

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        
        while(!queue.isEmpty()){
            int location[] = queue.poll();            	

            for(int direction = 0; direction<4; direction++){
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];
                
                if(r >= 0 && c >= 0 && r < N && c < M){
                    if(array[r][c] != 0 && !check[r][c]){
                        queue.offer(new int[] {r,c});
                        check[r][c] = true;
                        array[r][c] = array[location[0]][location[1]] + 1;
                    }
                }
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

	public String readString() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
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