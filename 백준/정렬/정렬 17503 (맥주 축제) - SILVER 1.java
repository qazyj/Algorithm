import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            int v = in.nextInt();
            int c = in.nextInt();

            pq.add(new Node(v,c));
        }

        int total = 0;
        int answer = -1;
        PriorityQueue<Integer> pre = new PriorityQueue<>();
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            //System.out.println(node.v + " " + node.c);

            pre.add(node.v);
            total += node.v;

            if(pre.size() > n) {
                total -= pre.poll();
            }
            if(pre.size() == n && total >= m) {
                answer = node.c;
                break;
            }
        }
        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int v;
    int c;

    public Node(int v, int c) {
        this.v = v;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        if(this.c == o.c) {
            return o.v - this.v;
        }
        return this.c - o.c;
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