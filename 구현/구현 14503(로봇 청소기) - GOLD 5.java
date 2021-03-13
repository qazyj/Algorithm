import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	static int[] x =  { -1, 0, 1, 0 };
    static int[] y = { 0, 1, 0, -1 };
	static int[][] array;
	static Queue<int[]> queue;
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
		array = new int[N][M];
		queue = new LinkedList<>();
		queue.offer(new int[] {in.nextInt(), in.nextInt(), in.nextInt()});
		answer = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = in.nextInt();
			}
		}

		OperateRobot();
	}

	private static void OperateRobot() {
		while (!queue.isEmpty()) {
			int[] robot = queue.poll();
			int robotX = robot[0];
			int robotY = robot[1];
			int d = robot[2];
			// 청소
			array[robotX][robotY] = 2;

            boolean check = false;    // 4 방향 모두 갈 수 없을 때
            int r;
            int c;
            int nextD;
 
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;    
                r = robotX + x[d];    
                c = robotY + y[d];    

                if (r < 0 || c < 0 || r >= N || c >= M)   continue;
                
                //다음 이동할 위치가  청소되지 않은 곳이라면 간다.
                if (array[r][c] == 0) {
                    queue.add(new int[] {r,c, d});
        			answer++;
                    check = true;
                    break;
                }
            }
            
            // 네 방향 모두 청소됐거나 벽일 경우 후진
            if (!check) {
                nextD = (d + 2) % 4;
                r = robotX + x[nextD];
                c = robotY + y[nextD];
 
                // 후진을 못할 경우
                if (array[r][c] != 1) {
                    queue.add(new int[] {r, c, d});
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