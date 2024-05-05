import java.util.*;

class Solution {
    static char[][] arr;
    static int[][][] dis;
    static int n,m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(String[] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length();

        arr = new char[n][m];
        dis = new int[n][m][2];
        int x=0;
        int y=0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m ;j++) {
                arr[i][j] = maps[i].charAt(j);
                dis[i][j][0] = 10001;
                dis[i][j][1] = 10001;
                if(arr[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }

        return bfs(x, y);
    }

    public int bfs(int i, int j) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(i, j, 0 , 0));
        dis[i][j][0] = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(arr[node.x][node.y] == 'E' && node.isLever==1) {
                return node.distance;
            }

            for(int d = 0; d < 4; d++) {
                int r = node.x + dx[d];
                int c = node.y + dy[d];

                if(r < 0 || c < 0 || r >= n || c >= m) continue;
                if(arr[r][c] == 'X') continue;
                if(dis[r][c][node.isLever] <= node.distance+1) continue;

                if(arr[r][c] == 'L') {
                    dis[r][c][1] = node.distance+1;
                    pq.add(new Node(r,c, node.distance+1, 1));
                }
                else {
                    dis[r][c][node.isLever] = node.distance+1;
                    pq.add(new Node(r,c, node.distance+1, node.isLever));
                }
            }
        }
        return -1;
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int distance;
    int isLever;

    public Node(int x, int y, int distance, int isLever) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.isLever = isLever;
    }

    @Override
    public int compareTo(Node n) {
        return this.distance - n.distance;
    }
}