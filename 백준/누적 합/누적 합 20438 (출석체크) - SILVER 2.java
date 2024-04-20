import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int m = in.nextInt();

        boolean[] visited = new boolean[n+3];
        List<Integer> sleepStudents = new ArrayList<>();
        for(int i = 0; i < k; i++) sleepStudents.add(in.nextInt());

        for(int i = 0; i < q; i++) {
            int input = in.nextInt();
            if(sleepStudents.contains(input)) continue;
            if(visited[input]) continue;

            for(int j = input; j < n+3; j++) {
                if(j%input != 0) continue;
                if(sleepStudents.contains(j)) continue;

                visited[j] = true;
            }
        }

        int[] sum = new int[n+3];
        for(int i = 3; i < n+3; i++) {
            sum[i] = sum[i-1]+ (!visited[i]? 1 : 0);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            sb.append((sum[e]-sum[s-1])).append("\n");
        }
        System.out.println(sb);
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