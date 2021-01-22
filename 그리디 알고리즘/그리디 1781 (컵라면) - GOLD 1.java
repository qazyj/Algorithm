import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMaxValue());
	}
	
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		N = in.readInt();
		array = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			array[i][0] = in.readInt();
			array[i][1] = in.readInt();
		}
		
		// 2차원 배열 정렬 (deadline 오름차순)
		Arrays.sort(array, new Comparator<int[]>() {
		    @Override
		    public int compare(int[] o1, int[] o2) {
		        return o1[0] - o2[0];
		    }
		});
	}
	
	private static int FindMaxValue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            queue.add(array[i][1]);
            while (!queue.isEmpty() && queue.size() > array[i][0]) 
            	queue.poll();
        }
        while (!queue.isEmpty())
            sum += queue.poll();
		return sum;
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
