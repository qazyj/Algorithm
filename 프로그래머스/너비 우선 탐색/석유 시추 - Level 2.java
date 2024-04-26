import java.util.*;

class Solution {
    static int n, m;
    static int[] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        arr = new int[m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0 || visited[i][j]) continue;

                bfs(land, visited, i, j);
            }
        }

        return Arrays.stream(arr).max().getAsInt();
    }

    public void bfs(int[][] land, boolean[][] visited, int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        int count = 1;
        Set<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            Node node = q.poll();
            set.add(node.y);

            for (int d = 0; d < 4; d++) {
                int r = node.x + dx[d];
                int c = node.y + dy[d];

                if (r < 0 || c < 0 || r >= n || c >= m) continue;
                if (land[r][c] == 0 || visited[r][c]) continue;

                q.add(new Node(r, c));
                visited[r][c] = true;
                count += 1;
            }
        }

        for (int index : set) {
            arr[index] += count;
        }
    }
}

class Node{
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}