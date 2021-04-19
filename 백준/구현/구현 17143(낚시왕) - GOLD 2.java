import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Algorithm {
	static int R, C, M, answer;
	static ArrayList<Shark> sharks;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		R = in.nextInt();
		C = in.nextInt();
		M = in.nextInt();
		answer = 0;
		sharks = new ArrayList<>();

		int r,c,s,d,z;
		for(int i = 0 ; i < M; i++) {
			r = in.nextInt();
			c = in.nextInt();
			s = in.nextInt();
			d = in.nextInt();
			z = in.nextInt();
			
			if(d == 1 || d == 2) s = s % (R*2-2);
			if(d == 3 || d == 4) s = s % (C*2-2);
			sharks.add(new Shark(r,c,s,d,z));
		}

		solve();
	}
	
	private static void solve() {
		for(int person = 1; person <= C; person++) {
			Collections.sort(sharks);
			
			// 잡아먹음
			int r = -1, c = -1;
			for(int i = 0; i < sharks.size(); i++) {
				int tempR = sharks.get(i).r;
				int tempC = sharks.get(i).c;
				
				if(r == tempR && c == tempC) {
					sharks.remove(i--);
				} else {
					r = tempR;
					c = tempC;
				}
			}
			
			for(int i = 0; i < sharks.size(); i++) {
				if(sharks.get(i).c > person)
					break;
				// 낚시
				if(sharks.get(i).c == person) {
					answer += sharks.get(i).z;
					sharks.remove(i);
					break;
				}
			}
			
			for(int i = 0; i < sharks.size(); i++) {
				r = sharks.get(i).r;
				c = sharks.get(i).c;
				int s = sharks.get(i).s;
				int d = sharks.get(i).d;
				
				// 물고기 이동
				for(int j = 0; j < s; j++) {					
					if(r + dx[d] <= 0 || c + dy[d] <= 0 || r + dx[d] >= R + 1 || c + dy[d] >= C+1) {
						if(d % 2 == 0)
							d--;
						else
							d++;
					}
					
					r += dx[d];
					c += dy[d];
				}

				sharks.get(i).SetR(r);
				sharks.get(i).SetC(c);
				sharks.get(i).SetD(d);					
			}
		}
	}
	
}

class Shark implements Comparable<Shark> {
	int r, c, s, d, z;

	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
	
	public void SetR(int r) {
		this.r = r;
	}
	
	public void SetC(int c) {
		this.c = c;
	}
	
	public void SetD(int d) {
		this.d = d;
	}

	@Override
	public int compareTo(Shark o) {
		if(this.c == o.c) {
			if(this.r == o.r)			
				return -Integer.compare(this.z, o.z);
			else
				return Integer.compare(this.r, o.r);
		} else
			return Integer.compare(this.c, o.c);
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