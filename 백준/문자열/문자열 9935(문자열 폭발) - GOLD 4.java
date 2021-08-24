import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Stack;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
        String BombedString = in.nextLine();
        String bombString = in.nextLine();
        sb = new StringBuilder();
        Stack<Character> stack = new Stack<Character> ();

        for(int i = BombedString.length() - 1; i >= 0;  i--){
            stack.push(BombedString.charAt(i));   

            if(stack.size() >= bombString.length() && stack.peek() == bombString.charAt(0)){
                boolean isBomb = true;
                for(int j = 1; j < bombString.length(); j++){
                    if(stack.get(stack.size()-j-1) != bombString.charAt(j)){
                        isBomb = false;
                        break;
                    }
                } 

                if(isBomb){  
                    for(int j = 0; j < bombString.length(); j++) 
                    	stack.pop();
                }
            }
        }
        

        int stackSize = stack.size();
        
        if(stack.isEmpty()){
               sb.append("FRULA");
        }else{
               for(int i = 0; i < stackSize; i++) sb.append(stack.pop());
        }
	}

}
/*
class Node implements Comparable<Node> {
	int number;
	double fail;
	
	public Node(int number, double fail) {
		this.number = number;
		this.fail = fail;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if(this.fail == o.fail)
			return Integer.compare(this.number, o.number);
		return -Double.compare(this.fail, o.fail);
	}
}*/

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
