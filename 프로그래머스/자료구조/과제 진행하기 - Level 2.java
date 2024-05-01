import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);

        String[] a = solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}});
        for(int i = 0; i < a.length; i++) System.out.println(a[i]);
        System.out.println();
    }

    public static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Play> p = new PriorityQueue<>();
        for(int i = 0; i < plans.length; i++) {
            p.add(new Play(plans[i][0], getTimeToInt(plans[i][1]), Integer.parseInt(plans[i][2])));
        }

        List<String> complete = new ArrayList<>();
        Stack<RestPlay> rest = new Stack<>();
        Playing cur = new Playing(p.poll());
        while(!p.isEmpty()) {
            Playing next = new Playing(p.poll());

            if(cur.start_time+cur.time <= next.start_time) {
                int rest_time = next.start_time-(cur.start_time+cur.time);
                complete.add(cur.name);

                while(!rest.isEmpty() && rest_time > 0) {
                    RestPlay rp = rest.pop();

                    if(rest_time >= rp.time) {
                        complete.add(rp.name);
                        rest_time -= rp.time;
                    } else {
                        rest.push(new RestPlay(rp.name, rp.time-rest_time));
                        break;
                    }
                }
            } else {
                System.out.println((cur.start_time+cur.time)-next.start_time);
                rest.push(new RestPlay(cur.name, (cur.start_time+cur.time)-next.start_time));
            }
            cur = next;
        }
        complete.add(cur.name);

        while(!rest.isEmpty()) {
            complete.add(rest.pop().name);
        }

        int index = 0;
        for(String s : complete) {
            answer[index++] = s;
        }

        return answer;
    }

    public static int getTimeToInt(String s) {
        String[] temp = s.split(":");
        return Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
    }
}
class Play implements Comparable<Play> {
    String name;
    int start_time;
    int time;

    public Play(String name, int start_time, int time) {
        this.name = name;
        this.start_time = start_time;
        this.time = time;
    }

    @Override
    public int compareTo(Play p) {
        return this.start_time - p.start_time;
    }
}

class Playing {
    String name;
    int start_time;
    int time;

    public Playing(Play p) {
        this.name = p.name;
        this.start_time = p.start_time;
        this.time = p.time;
    }
}

class RestPlay {
    String name;
    int time;

    public RestPlay(String name, int time) {
        this.name = name;
        this.time = time;
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