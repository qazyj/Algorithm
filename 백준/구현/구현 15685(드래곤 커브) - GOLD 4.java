import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algorithm {
	static int N, answer;
	static boolean[][] check;
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		answer = 0;
		check = new boolean[100][100];
		
		for(int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int d = in.nextInt();
			int g = in.nextInt();
			
			DrawLine(x, y, GetDirections(d, g));
		}
		
		// 사각형 개수
		for(int i = 0; i < 99; i++) {
			for(int j = 0; j < 99; j++) {
				if(check[i][j] && check[i+1][j] && check[i][j+1] && check[i+1][j+1])
					answer++;
			}
		}
	}
	
    public static List<Integer> GetDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
 
        for(int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                int direction = (directions.get(j) + 1) % 4;
                directions.add(direction);
            }
        }
        return directions;
    }
 
    public static void DrawLine(int x, int y, List<Integer> directions) {
        check[x][y] = true;
 
        for (int direction : directions) {
            switch (direction) {
                case 0:	// 오른쪽
                    check[++x][y] = true;
                    break;
                case 1:	// 위
                    check[x][--y] = true;
                    break;
                case 2:	// 왼쪽
                    check[--x][y] = true;
                    break;
                case 3:	// 아래
                    check[x][++y] = true;
                    break;
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