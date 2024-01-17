import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int T = in.nextInt();
        int n = in.nextInt();
        int[] arrA = new int[n];
        for (int i = 0; i < n; i++) arrA[i] = in.nextInt();
        int m = in.nextInt();
        int[] arrB = new int[m];
        for (int i = 0; i < m; i++) arrB[i] = in.nextInt();

        //부배열 쌍
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();


        //부배열 구하기
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arrA[j];
                listA.add(sum);
            }
        }
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += arrB[j];
                listB.add(sum);
            }
        }

        Collections.sort(listA);
        Collections.sort(listB);

        int aSize = listA.size();

        long cnt = 0;
        int leftPointer = 0;
        int rightPointer = listB.size() - 1;

        while (leftPointer < aSize && rightPointer >= 0) {
            int sum = listA.get(leftPointer) + listB.get(rightPointer);
            if (sum == T) {

                int a = listA.get(leftPointer);
                int b = listB.get(rightPointer);
                long leftCnt = 0;
                long rightCnt = 0;

                while (leftPointer < aSize && listA.get(leftPointer) == a) {
                    leftCnt++;
                    leftPointer++;
                }
                while (rightPointer >= 0 && listB.get(rightPointer) == b) {
                    rightCnt++;
                    rightPointer--;
                }
                cnt += leftCnt * rightCnt;
            } else if (sum < T) {
                leftPointer++;
            } else if (sum > T) {
                rightPointer--;
            }
        }


        System.out.println(cnt);
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