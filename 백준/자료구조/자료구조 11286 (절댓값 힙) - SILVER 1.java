import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		int N = in.nextInt();
		sb = new StringBuilder();
		PriorityQueue<Node> pq = new PriorityQueue<>();

		while(N-->0) {
			int input = in.nextInt();
			if(input == 0) {
				if(pq.size() == 0) {
					// 큐가 비어있다면 0 출력
					sb.append("0").append("\n");
				} else {
					// 큐가 비어있지 않다면 큐에 저장한 값 중 가장 작은 값 출력
					sb.append(pq.poll().value).append("\n");
				}
				continue;
			}

			// 입력이 0이 아니라면 큐에 입력값과 절댓값 추가
			int absoluteInput = Math.abs(input);
			pq.add(new Node(input, absoluteInput));
		}
	}
}

class Node implements Comparable<Node> {
	int value;
	int absoluteValue;

	public Node(int value, int absoluteValue) {
		this.value = value;
		this.absoluteValue = absoluteValue;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		// 절댓값 값이 같다면 입력값이 가장 작은 값
		if(this.absoluteValue == o.absoluteValue) {
			return this.value - o.value;
		} else {
			// 절댓값이 다르다면 절댓값으로 오름차순 정렬
			return this.absoluteValue - o.absoluteValue;
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