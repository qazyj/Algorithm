import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();
        long sum = 0;
        List<Town> towns = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int index = in.nextInt();
            int person = in.nextInt();
            towns.add(new Town(index, person));
            sum += person;
        }
        Collections.sort(towns);
        long answer = 0;
        for(int i = 0; i < n; i++) {
            answer += towns.get(i).person;
            if((sum+1)/2 <= answer) {
                System.out.println(towns.get(i).index);
                break;
            }
        }
    }
}

class Town implements Comparable<Town> {
    int index;
    int person;

    public Town(int index, int person) {
        this.index = index;
        this.person = person;
    }


    @Override
    public int compareTo(Town o) {
        return this.index - o.index;
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