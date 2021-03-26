import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Algorithm {
	static int[] array;
	static int N, M, L, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		BinarySearch(0, L - 1);
		System.out.println(answer);
	}

	// µ•¿Ã≈Õ
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.readInt();
		M = in.readInt();
		L = in.readInt();

		array = new int[N + 2];
		array[N + 1] = L;

		for (int i = 1; i <= N; i++)
			array[i] = in.readInt();
		Arrays.sort(array);
	}

	private static void BinarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (isPossible(mid)) 
				left = mid + 1;
			else 
				right = mid - 1;			
		}

		answer = left;
	}

	public static boolean isPossible(int mid) {
		int sum = 0;
		for (int i = 0; i < N + 1; i++) {
			sum += (array[i + 1] - array[i] - 1) / mid;
		}
		return M < sum;
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
