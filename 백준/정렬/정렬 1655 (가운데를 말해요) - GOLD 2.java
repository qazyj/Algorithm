import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static PriorityQueue<Integer> leftQueue;
    static PriorityQueue<Integer> rightQueue;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();
        leftQueue = new PriorityQueue<>(Collections.reverseOrder());
        rightQueue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int input = in.nextInt();
            if (i % 2 != 0) {
                leftQueue.add(input);
                changeNumber();
                sb.append(leftQueue.peek()).append("\n");
            } else {
                rightQueue.add(input);
                changeNumber();
                sb.append(leftQueue.peek()).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void changeNumber() {
        if (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            int leftNumber = leftQueue.peek();
            int rightNumber = rightQueue.peek();

            if (leftNumber > rightNumber) {
                leftNumber = leftQueue.poll();
                rightNumber = rightQueue.poll();
                leftQueue.add(rightNumber);
                rightQueue.add(leftNumber);
            }
        }

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