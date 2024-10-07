import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                pq.add(Integer.parseInt(s[j]));
            }
        }

        int count = 0;
        int answer = 0;
        while(count != n) {
            answer = pq.poll();
            count++;
        }
        System.out.println(answer);
    }
}