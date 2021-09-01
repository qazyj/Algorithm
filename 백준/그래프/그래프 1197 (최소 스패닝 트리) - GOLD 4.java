import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
	static int V, E, answer;
	static int[] parent;
	static PriorityQueue<Node> node;
	
	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	// 데이터
	private static void SetData() throws Exception {
		InputReader in = new InputReader(System.in);
		
		V = in.nextInt();
		E = in.nextInt();
		answer = 0;
		parent = new int[V+1];
		node = new PriorityQueue<>();
		
        for(int i=0; i<=V; i++) 
            parent[i] = i;
        
		
        for(int i=0; i<E; i++) 
            node.add(new Node(in.nextInt(),in.nextInt(),in.nextInt()));

        for(int i=0; i<E; i++) {
            Node tempNode = node.poll();
            
            int a = find(tempNode.a);
            int b = find(tempNode.b);
            
            if(a==b) continue;
            union(a,b);
            answer += tempNode.c;
        }        
	}
	
	//크루스칼
    public static int find(int a) {
        if(a == parent[a]) return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }
    
    public static void union(int a,int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA != rootB) 
            parent[rootA] = b;

    }
}

class Node implements Comparable<Node>{
	int a;
	int b;
	int c;
	
	public Node(int a,int b,int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
    @Override
    public int compareTo(Node node) {
        // TODO Auto-generated method stub
        return this.c - node.c;
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