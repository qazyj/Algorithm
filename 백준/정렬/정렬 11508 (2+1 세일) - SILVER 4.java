import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) pq.add(Integer.parseInt(br.readLine()));

        int answer = 0;
        while(!pq.isEmpty()) {
            int size = 2;
            while(!pq.isEmpty() && size-- > 0) {
                answer += pq.poll();
            }
            if(!pq.isEmpty()) pq.poll();
        }
        System.out.println(answer);
    }
}