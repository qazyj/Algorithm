import java.io.*;
import java.util.*;

public class Algorithm {
	static int N;
	static StringBuilder sb;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		sb = new StringBuilder();
		array = new int[N + 1][N + 1];
		
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N + 1; j++) {
                array[i][j] = 987654321;
            }
        }
        for(int i = 1 ; i < N+1; i++) {
            array[i][i] = 0;
        }
        
		while (true) {
			int a = in.nextInt();
			int b = in.nextInt();
			if (a == -1 && b == -1)
				break;

			array[a][b] = 1;
			array[b][a] = 1;
		}

		FloydWarshall();
	}

	private static void FloydWarshall() {
        for(int k = 1; k < N + 1; k++) {
            for (int a = 1; a < N + 1; a++) {
                for (int b = 1; b < N + 1; b++) {
                    if (array[a][b] > array[a][k] + array[k][b]) {
                        array[a][b] = array[a][k] + array[k][b];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < N+1; i++) {
            int max_val = 0;
            for (int j = 1; j < N + 1; j++) {
                max_val = Math.max(max_val, array[i][j]);
            }
            min = Math.min(min, max_val);
        }

        ArrayList<Integer> lists = new ArrayList<>();
        for(int i = 1; i < N+1; i++) {
            int max = 0;
            for (int j = 1; j < N + 1; j++) {
                max = Math.max(max, array[i][j]);
            }
            if(max == min) {
                lists.add(i);
            }
        }

        sb.append(min + " " + lists.size()).append("\n");
        for(int list : lists) {
            sb.append(String.valueOf(list) + " ");
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