import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int N, answer;
	static Flower[] flowers;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		flowers = new Flower[N];

		for(int i = 0; i < N; i++) {
			int startMonth = in.nextInt();
			int startDay = in.nextInt();
			int endMonth = in.nextInt();
			int endDay = in.nextInt();

			int start = startMonth * 100 + startDay;
			int end = endMonth * 100 + endDay;
			flowers[i] = new Flower(start, end);
		}

		Arrays.parallelSort(flowers);	// 조건에 맞게 정렬

		int endDay = 1201;
		int start = 301;
		int count = 0;
		int max = 0;
		int index = 0;

		// 현재 종료일이 12월 1일보다 적은 경우만 가능(12월 1일 초과면 이미 조건 만족했기 때문에)
		while(start < endDay) {
			boolean isFinded = false;	// 새 꽃 찾은지 여부 확인

			for(int i = index; i < N; i++) {
				if(flowers[i].start > start) {
					break;
				}

				if(max < flowers[i].end) {
					isFinded = true;
					max = flowers[i].end;
					index = i + 1;
				}
			}

			if(isFinded) {
				start = max;
				count++;
			}
			else {
				break;
			}
		}

		if(max >= endDay) {
			answer = count;
		}
	}
}

class Flower implements Comparable<Flower> {
	int start;
	int end;

	Flower(int start, int end) {
		this.start = start;
		this.end = end;
	}

	// 1) 시작일 낮은 순
	// 2) 종료일 높은 순
	@Override
	public int compareTo(Flower flower) {
		if(this.start < flower.start) {
			return -1;
		}
		else if(this.start == flower.start) {
			return flower.end - this.end;
		}
		else {
			return 1;
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