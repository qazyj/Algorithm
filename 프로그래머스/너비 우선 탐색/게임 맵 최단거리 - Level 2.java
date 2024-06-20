import java.util.*;

class Solution {
    static int n,m,answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(int[][] maps) {
        answer = Integer.MAX_VALUE;
        n = maps.length;
        m = maps[0].length;
        bfs(maps);
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    public void bfs(int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,1));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node.x == n-1 && node.y == m-1) {
                answer = node.distance;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int r = node.x + dx[i];
                int c = node.y + dy[i];

                if(r < 0 || c < 0 || r >= n || c >= m) continue;
                if(visited[r][c] || maps[r][c] == 0) continue;

                visited[r][c] = true;
                q.add(new Node(r,c,node.distance+1));
            }
        }
    }
}

class Node {
    int x;
    int y;
    int distance;

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}