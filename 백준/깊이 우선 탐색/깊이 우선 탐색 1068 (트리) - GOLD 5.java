import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
	static int answer, n, remove;
	static List[] list;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		n = in.nextInt();
		list = new ArrayList[n];
		answer = 0;
		int root = 0;
		for(int i = 0 ; i < n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for(int i = 0; i < n; i++) {
			int parent = in.nextInt();
			if(parent == -1) {
				root = i;
				continue;
			}
			list[parent].add(i);
		}
		remove = in.nextInt();
		removeNode(remove);
		if(remove == root) {
			System.out.println(0);
		}else {
			System.out.println(findLeaf(root));
		}
	}

	static void removeNode(int node) {

		// 해당 노드 자식노드 모두 조회
		if(list[node].size()>0) {
			int size = list[node].size();
			while(size>0) {
				int child = (int)list[node].get(--size);
				removeNode(child);
			}
		}

		// 해당 노드 자식 노드 모두 삭제
		for(int i=0; i<n; i++) {
			if(list[i].contains(node)) {
				for(int j=0; j<list[i].size(); j++) {
					if((int) list[i].get(j) == node) {
						list[i].remove(j);
					}
				}
			}
		}
	}

	static int findLeaf(int node) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node);
		int cnt=0;

		while(!q.isEmpty()) {
			int pos = q.poll();
			if(list[pos].size()==0) {
				cnt++; // 리프노드 count
				continue;
			}

			for(int i = 0; i < list[pos].size(); i++) {
				q.add((int)list[pos].get(i));
			}
		}
		return cnt;
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