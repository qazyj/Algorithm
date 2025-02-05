import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.next());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.add(new Node(Integer.parseInt(sc.next())));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll().x).append("\n");
        }

        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    int x;

    public Node(int x) {
        this.x = x;
    }

    @Override
    public int compareTo(Node n) {
        return this.x - n.x;
    }
}