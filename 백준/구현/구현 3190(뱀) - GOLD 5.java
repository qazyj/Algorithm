import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
	static int N, K, L, answer, r, c, direction;
	static int[][] array;
	static Queue<Direction> queue;
	static ArrayList<int []> snake;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = 0;
		array = new int[N + 1][N + 1];
		K = in.nextInt();
		queue = new LinkedList<>();
		snake = new ArrayList<int []>();
		snake.add(new int[] {1,1});
		direction = 1;
		
		for(int i = 0; i < K; i++)
			array[in.nextInt()][in.nextInt()] = 2;
		
		L = in.nextInt();
		for(int i = 0; i < L; i++)
			queue.add(new Direction(in.nextInt(), in.nextLine().charAt(0)));
		
		array[1][1] = 1;
		MoveSnake();
	}

	public static void MoveSnake() {
		int row = 1;
		int col = 1;
		
        while (true) {
        	// 방향 바꿔야 되는 경우 (direction 변경)
            if (!queue.isEmpty() && answer == queue.peek().count) {
            	ChangeDirection(queue.peek().direction);
                queue.poll();
            }

            r = row + dx[direction];
            c = col + dy[direction];
            snake.add(new int[] {r,c});

            answer++;
            if (r <= 0 || c <= 0 || r > N || c > N) {
                break;
            }

            if (array[r][c] == 2) { // 사과 O
                array[r][c] = 1;
            } else if (array[r][c] == 0) {    // 사과 X
                array[r][c] = 1;
                array[snake.get(0)[0]][snake.get(0)[1]] = 0;
                snake.remove(0);
            } else {
                break;
            }

            row = r;
            col = c;
        }
    }
	
	private static void ChangeDirection(char LorD) {
        if (LorD == 'D') { // 우회전
            direction = (direction + 1) % 4;
        } else if (LorD == 'L') {  // 좌회전
            direction = (direction + 3) % 4;
        }
	}
}

class Direction {
	int count;
	char direction;
	
	Direction(int count, char direction){
		this.count = count;
		this.direction = direction;
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