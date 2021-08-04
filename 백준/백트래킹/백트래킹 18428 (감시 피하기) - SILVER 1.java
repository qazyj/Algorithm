import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Main {
	static int N;
	static char[][] array;
	static ArrayList<int []> teachers;
	static ArrayList<int []> empty;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println("NO");
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		N = in.nextInt();
		array = new char[N][N];
		teachers = new ArrayList<>();
		empty = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String temp = in.nextLine();
			for(int j = 0; j < N; j++) {
				array[i][j] = temp.charAt(j*2);
				if(array[i][j] == 'T')
					teachers.add(new int[] {i,j});
				if(array[i][j] == 'X')
					empty.add(new int[] {i,j});
			}
		}
		
		SaveAnswer(0, 0);
	}
	
	private static void SaveAnswer(int count, int index) {
		// basecase
		if(count == 3) {			
			if(NotMeet()) {
				System.out.println("YES");
				System.exit(0);
			}			
			return;
		}
		if(index >=N*N) return;
		/*
		for(int i = index; i < empty.size(); i++) {
	        array[empty.get(i)[0]][empty.get(i)[1]] = 'O'; 
	        SaveAnswer(count + 1, index + 1);
	        array[empty.get(i)[0]][empty.get(i)[1]] = 'X';
		}*/
		int r = index / N;
		int c = index % N;
		if(array[r][c] == 'X') {
			array[r][c] = 'O';
			SaveAnswer(count+1, index+1);
			array[r][c] = 'X';
		}
		SaveAnswer(count, index+1);
	}
	
	private static boolean NotMeet() {
		for(int t = 0; t < teachers.size(); t++) {
			int i = teachers.get(t)[0];
			int j = teachers.get(t)[1];
			
			// 왼
			for(int directon = j-1; directon >= 0; directon--) {
				if(array[i][directon] == 'S')
					return false;
				if(array[i][directon] == 'O')
					break;
			}
			
			// 오른
			for(int directon = j+1; directon < N; directon++) {
				if(array[i][directon] == 'S')
					return false;
				if(array[i][directon] == 'O')
					break;
			}
			
			// 위
			for(int directon = i-1; directon >= 0; directon--) {
				if(array[directon][j] == 'S')
					return false;
				if(array[directon][j] == 'O')
					break;
			}
			
			// 아래
			for(int directon = i+1; directon < N; directon++) {
				if(array[directon][j] == 'S')
					return false;
				if(array[directon][j] == 'O')
					break;
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