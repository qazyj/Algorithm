import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Algorithm {
	static int R, C, M, answer;
	static Shark shark;
	static ArrayList<Shark> sharks;
	static int[] dx = { 0, 1, -1, 0, 0 };
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

		for(int i = 0 ; i < M; i++) {
			sharks.add(new Shark(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()));
		}

		solve();
	}
	
	private static void solve() {
		for(int person = 1; person <= C; person++) {
			Collections.sort(sharks);
			
			System.out.println("\n" + person);
			for(int i = 0 ; i < sharks.size(); i++) 
				System.out.println(sharks.get(i).r + " " + sharks.get(i).c + " " + answer);
		
			
			// 잡아먹음
			int r = -1, c = -1;
			for(int i = 0; i < sharks.size(); i++) {
				if(r == sharks.get(i).r && c == sharks.get(i).c) {
					sharks.remove(i--);
				} else {
					r = sharks.get(i).r;
					c = sharks.get(i).c;
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
					if(r+dx[d] <= 0 || c+dy[d] <= 0) 
						d--;
					else if(r+dx[d] > R || c+dy[d] > C)
						d++;
					
					r += dx[d];
					c += dy[d];
				}

				sharks.get(i).r = r;
				sharks.get(i).c = c;
				sharks.get(i).d = d;							
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

	@Override
	public int compareTo(Shark o) {
		if(this.c == o.c) {
			if(this.z != o.z)			
				return Integer.compare(this.r, o.r);
			else
				return Integer.compare(this.z, o.z);
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