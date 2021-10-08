import java.util.*;

class Solution {
    static boolean[] check;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[n];

        for(int i = 0 ; i < n; i++){
            if(check[i]) continue;

            bfs(i, n, computers);
            answer++;
        }
        return answer;
    }

    public static void bfs(int i, int n, int[][] computers) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        check[i] = true;

        while(!queue.isEmpty()) {
            int temp = queue.poll();

            for(int j = 0 ; j < n; j++) {
                if(computers[temp][j] == 0 || check[j]) continue;

                check[j] = true;
                queue.add(j);
            }
        }
    }
}