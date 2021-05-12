import java.io.*;
import java.util.*;

public class Algorithm {
    static int n,m,k,r,c;
    static int array[][];
    static int answer;
    static int stiker[][];
    
    public static void main(String[] args) throws IOException{
    	InputReader in = new InputReader(System.in);
        
        n =	in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        array = new int[n][m];
        answer = 0;
        
        while(k-- >0) {
            r = in.nextInt();
            c = in.nextInt();
            stiker = new int[r][c];
            for(int i=0; i<r; i++) {
                for(int j=0; j<c; j++) {
                    stiker[i][j] = in.nextInt();
                }
            }
            
            for(int i=0; i<4; i++) {
                if(put_stiker()) {
                    break;
                }
                else {
                    rotate();
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(array[i][j]==1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
    public static boolean put_stiker() {
        for(int i=0; i<=n-r; i++) {
            for(int j=0; j<=m-c; j++) {
                if(isPossible(i,j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isPossible(int row, int col) {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(stiker[i][j]==1 && array[row+i][col+j]==1) {
                    return false;
                }
            }
        }
        
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(stiker[i][j]==1) {
                    array[row+i][col+j]=1;
                }
            }
        }
        return true;
    }
    public static void rotate() {
        int rotate_map[][] = new int[r][c];
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                rotate_map[i][j] = stiker[i][j];
            }
        }
        
        stiker = new int[c][r];
        
        for(int i=0; i<c; i++) {
            for(int j=0; j<r; j++) {
                stiker[i][j] = rotate_map[r-j-1][i];
            }
        }
        int tmp = r;
        r=c;
        c=tmp;
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