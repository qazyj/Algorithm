import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int M;
	static HashSet<Integer> set;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);

		M = in.nextInt();
		set = new LinkedHashSet<>();
		sb = new StringBuilder();		
		
        for(int i = 0; i < M; i++){
            String[] s = in.nextLine().split(" ");
            String command = s[0];

            switch (command){
                case "add" :                	
                    set.add(Integer.parseInt(s[1]));
                    break;

                case "remove":
                    set.remove(Integer.parseInt(s[1]));
                    break;

                case "check" :
                    if(set.contains(Integer.parseInt(s[1]))) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                    
                case "toggle" :
                    if(set.contains(Integer.parseInt(s[1]))) set.remove(Integer.parseInt(s[1]));
                    else set.add(Integer.parseInt(s[1]));
                    break;

                case "all" :
                    for(int num = 1; num<=20; num++){
                        set.add(num);
                    }
                    break;
                    
                case "empty" :
                    set.clear();
                    break;
            }

        }
	}
	
	//private static int SaveAnswer() {
	//}	
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