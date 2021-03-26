import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    static int N, M, max;
    static int[][] array;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		SetData();
        bfs();
        
        if (!check()) {
            System.out.println(-1);
        } else if(max==0){
            System.out.println(max);
        }else{
            System.out.println(max-1);
        }
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.readInt();		
        M = in.readInt();
        array = new int[M][N];
        max = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int M = in.readInt();
                array[i][j] = M;
                if (M == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
		
	}

    static public void bfs() {
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            
            for (int direction = 0; direction < 4; direction++) {            	
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];
                
                if (0 > r || r >= M || 0 > c || c >= N) continue;
                
                if (array[r][c] == 0) {
                    array[r][c] = array[location[0]][location[1]] + 1;
                    queue.offer(new int[]{r, c});
                    if (array[r][c] > max) {
                        max = array[r][c];
                    }
                }
                
            }
        }
    }

    static public boolean check() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (array[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
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
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
