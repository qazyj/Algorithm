import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        boolean[] check = new boolean[n+1];
        boolean[][] node = new boolean[n+1][n+1];

        for (int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            node[a][b] = true;
            node[b][a] = true;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        check[1] = true;

        while (!queue.isEmpty()) {
            answer = queue.size();
            System.out.println(answer);
            for (int i=0; i<answer; i++) {
                int pre = queue.poll();
                for (int next=2; next<=n; next++) {
                    if (node[pre][next] && !check[next]) {
                        check[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return answer;
    }
}