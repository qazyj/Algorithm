import java.util.*;

class Solution {
    static char[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n, m;

    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        arr = new char[n][m];
        int x = 0;
        int y = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = board[i].charAt(j);

                if(arr[i][j] == 'R') {
                    x = i;
                    y = j;
                }
            }
        }

        return bfs(x, y);
    }

    public int bfs(int i, int j) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new Node(i, j, 0));
        visited[i][j] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(arr[node.x][node.y] == 'G') {
                return node.distance;
            }

            for(int d = 0; d < 4; d++) {
                int r = node.x + dx[d];
                int c = node.y + dy[d];

                while(r >=0 && c >= 0 && r < n && c < m && arr[r][c] != 'D') {
                    r += dx[d];
                    c += dy[d];
                }
                r -= dx[d];
                c -= dy[d];

                if(visited[r][c]) continue;

                visited[r][c] = true;
                q.add(new Node(r,c, node.distance+1));
            }
        }
        return -1;
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