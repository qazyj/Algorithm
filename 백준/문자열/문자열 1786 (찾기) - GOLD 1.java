import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Algorithm {
	static StringBuilder sb;
	static int start, count;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(list.size() + "\n" + sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String t = br.readLine();
		String p = br.readLine();
		sb = new StringBuilder();
		list = new ArrayList<Integer>();
		count = 0;

		KMP(t, p);
		for(int value : list) {
			sb.append(value).append(" ");
		}
	}
	
	// KMP 알고리즘 - pi[] 배열에서 주어진 문자열의 인덱스 0부터 i까지의 부분 문자열 중
	// 접두사 == 접미사가 되는 가장 긴 접두사의 길이를 넣는다.
	// 현재 밑 코드에서 접두사는 j(왼쪽), 접미사는 i(오른쪽)이다.
	static void KMP(String s, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0;
		int strLen = s.length();
		int patternLen = pattern.length();
		
		for(int i = 0; i < strLen; i++) {
			while(j > 0 && s.charAt(i) != pattern.charAt(j)) { // 다르면 같았던 다음 접미사로 바로 건너뛰기 
				j = pi[j - 1];
			}
			
			if(s.charAt(i) == pattern.charAt(j)) {
				if(j + 1 == patternLen) { // pattern 문자열 전부가 같다면
					list.add(i - j + 1); // 전체 문자열 중 패턴 문자열과 같은 문자열의 시작 인덱스를 구해야 하므로(게다가 1부터 시작함)
					j = pi[j]; // 초기화
				}
				else { // 바로 다음번째 문자를 가지고 비교해야 하므로 
					j++;
				}
			}
		}
	}

	static int[] getPi(String p) {
		int[] pi = new int[p.length()];
		int j = 0;
		for (int i = 1; i < p.length(); i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) { // j > 0 인 이유는 최소 두 글자부터 비교하기 위함
				j = pi[j - 1]; // 다르면 이전의 문자열에서 접두사 - 접미사 같은 최대 길로 이동시킨다.
			}
			if (p.charAt(i) == p.charAt(j))
				pi[i] = ++j;
		}

		return pi;
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