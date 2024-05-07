import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] arr;
    static int n, m;
    static List<Integer> answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        arr = new int[n][m];
        visited = new boolean[n][m];
        answer = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int value = maps[i].charAt(j)-'0';
                value=(value==40)?0:value;
                arr[i][j] = value;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(visited[i][j] || arr[i][j] == 0) continue;

                bfs(i,j);
            }
        }
        Collections.sort(answer);
        if(answer.size() == 0) answer.add(-1);
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public void bfs(int i, int j) {
        Queue<Node> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new Node(i, j));
        int sum = arr[i][j];
        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int d = 0; d < 4; d++) {
                int r = node.x + dx[d];
                int c = node.y + dy[d];

                if(r < 0 || c  < 0 || r >= n || c >= m) continue;
                if(visited[r][c] || arr[r][c] == 0) continue;

                sum += arr[r][c];
                visited[r][c] = true;
                q.add(new Node(r,c));
            }
        }
        answer.add(sum);

    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}