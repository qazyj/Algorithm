import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Algorithm {
	static int answer;
	static int[][] array;
	
	public static void main(String[] args) throws Exception {
		SetData();
        System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		array = new int[4][8];
		for(int i = 0; i < 4; i++) {
			String s = in.nextLine();
			for(int j = 0; j < 8; j++) {
				array[i][j] = s.charAt(j) - '0';
			}
		}
		
		int K = in.nextInt();
		for(int i = 0; i < K; i++) {
			TurnCogWheel(in.nextInt() - 1, in.nextInt());
		}
		
        answer = 0;
        int value = 1;
        for (int i = 0; i < 4; i++) {
        	answer += array[i][0] * value;
        	value *= 2;
        }
	}
	
	private static void TurnCogWheel(int wheelNumber, int direction) {
        left(wheelNumber - 1, -direction);
        right(wheelNumber + 1, -direction);
        rotate(wheelNumber, direction);
	}

    static void left(int wheelNumber, int direction) {
        if (wheelNumber < 0) return;

        if (array[wheelNumber][2] != array[wheelNumber + 1][6]) {
            left(wheelNumber - 1, -direction);
            rotate(wheelNumber, direction);
        }
    }

    static void right(int wheelNumber, int direction) {
        if (wheelNumber > 3) return;

        if (array[wheelNumber][6] != array[wheelNumber - 1][2]) {
            right(wheelNumber + 1, -direction);
            rotate(wheelNumber, direction);
        }
    }
    
    static void rotate(int wheelNumber, int direction) {
        if (direction == 1) {	// 시계
            int temp = array[wheelNumber][7];

            for (int i = 7; i > 0; i--) {
                array[wheelNumber][i] = array[wheelNumber][i - 1];
            }

            array[wheelNumber][0] = temp;

        } else {				// 반시계
            int temp = array[wheelNumber][0];

            for (int i = 0; i < 7; i++) {
                array[wheelNumber][i] = array[wheelNumber][i + 1];
            }

            array[wheelNumber][7] = temp;
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