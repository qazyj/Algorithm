import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++ ) {
            pq.add(new Node(in.nextInt(), in.nextInt()));
        }

        Node firstNode = pq.poll();
        int start = firstNode.left;
        int end = firstNode.right;
        int answer = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.left > end) {
                answer += end - start;
                start = cur.left;
                end = cur.right;
                continue;
            }

            if(cur.right > end) end = cur.right;
        }
        answer += end - start;
        System.out.println(answer);
    }
}

class Node implements Comparable<Node>{
    int left;
    int right;
    public Node(int left, int right){
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        if(this.left == node.left)
            return node.right - this.right;
        return this.left - node.left;
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