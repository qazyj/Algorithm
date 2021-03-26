import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
	static int[][] array;
	static int[] check;
	static int answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		array = new int[10][10];
		check = new int[5];
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				array[i][j] = in.nextInt();
			}
		}
		
		dfs(0);
		if(answer == Integer.MAX_VALUE)
			answer = -1;
	}

	private static void dfs(int y) {
        if(CheckSuccess()) {
            int count = 0;
            for(int i = 0 ; i < 5 ; i++) {
                count += check[i];
            }
            if(answer > count)
                answer = count;
            return;
        }
 
        for(int i = y ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                if(array[i][j] == 1) {
                    for(int Size = 4 ; Size >=0 ; Size--) {
                        if(check[Size] < 5 && CheckSquare(i, j, Size)) {
                            check[Size]++;
                            CoverSquare(i, j, Size);
                            dfs(i);
                            UncoverSquare(i, j, Size);
                            check[Size]--;
                        }
                    }
                    return;
                }
            }
        }
	}
	
	private static boolean CheckSquare(int y, int x, int Size) {
		// 규격을 벗어나는 경우
		if(x + Size >= 10 || y + Size >= 10) return false;
		
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                if(array[i][j] == 0)
                    return false;
            }
        }
        return true;
	}
	
	private static void CoverSquare(int y, int x, int Size) {	
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                array[i][j] = 0;
            }
        }
	}
	
	private static void UncoverSquare(int y, int x, int Size) {		
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                array[i][j] = 1;
            }
        }
	}
	
    private static boolean CheckSuccess() {
        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                if(array[i][j] == 1)
                    return false;
            }
        }
        return true;
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