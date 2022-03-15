import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();

		System.out.println(sb);
	}

	private static void SetData() {
		InputReader in = new InputReader(System.in);

		array = new int[200000][26];
		sb = new StringBuilder();

		int index = 0;
		while(true) {
			String s = in.nextLine();
			if(s.equals("-")) break;

			for(int i = 0; i < s.length(); i++) {
				array[index][s.charAt(i) - 'A']++;
			}
			index++;
		}

		while(true) {
			String s = in.nextLine();
			if(s.equals("#")) break;
			ArrayList<Alpha> list = new ArrayList<>();
			int[] temp = new int[26];
			for(int i = 0; i < s.length(); i++) {
				temp[s.charAt(i) - 'A']++;
			}

			for(int i = 0; i < s.length(); i++) {
				int count = 0;
				for(int j = 0; j < index; j++) {
					if(array[j][s.charAt(i) - 'A'] == 0) continue;
					boolean check = false;
					for(int z = 0; z < 26; z++) {
						if(array[j][z] > temp[z]) {
							check = true;
							break;
						}
					}
					if(check) continue;
					count++;
				}
				list.add(new Alpha(s.charAt(i), count));
			}

			Collections.sort(list);

			int min = list.get(0).count;
			int max = list.get(list.size()-1).count;
			PriorityQueue<Character> minChar = new PriorityQueue<>();
			Set<Character> minSet = new HashSet<>();
			PriorityQueue<Character>  maxChar = new PriorityQueue<>();
			Set<Character> maxSet = new HashSet<>();
			minChar.add(list.get(0).alpha);
			maxChar.add(list.get(list.size()-1).alpha);
			minSet.add(list.get(0).alpha);
			maxSet.add(list.get(list.size()-1).alpha);
			for(int i = 1; i < list.size(); i++) {
				if(min != list.get(i).count) break;
				if(minSet.contains(list.get(i).alpha)) continue;
				minChar.add(list.get(i).alpha);
				minSet.add(list.get(i).alpha);
			}

			for(int i = list.size()-2; i >= 0; i--) {
				if(max != list.get(i).count) break;
				if(maxSet.contains(list.get(i).alpha)) continue;
				maxChar.add(list.get(i).alpha);
				maxSet.add(list.get(i).alpha);
			}

			while(!minChar.isEmpty()) {
				sb.append(minChar.poll());
			}
			sb.append(" " + min + " ");
			while(!maxChar.isEmpty()) {
				sb.append(maxChar.poll());
			}
			sb.append(" " + max).append("\n");

		}
	}
}

class Alpha implements Comparable<Alpha> {
	char alpha;
	int count;

	public Alpha(char alpha, int count) {
		this.alpha = alpha;
		this.count = count;
	}

	@Override
	public int compareTo(Alpha o) {
		// TODO Auto-generated method stub
		return this.count - o.count;
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