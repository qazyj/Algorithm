import java.util.*;

class Solution {
    static ArrayList<Integer>[] lists;
    static int[] dis;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        lists = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i < roads.length; i++) {
            lists[roads[i][0]].add(roads[i][1]);
            lists[roads[i][1]].add(roads[i][0]);
        }

        dis = new int[n+1];
        for(int i = 0; i <= n; i++) dis[i] = 9999999;
        bfs(n, destination);
        for(int i = 0; i < sources.length; i++) {
            answer[i] = (dis[sources[i]]==9999999)?-1:dis[sources[i]];
        }

        return answer;
    }

    public void bfs(int n, int cur) {
        Queue<Node> pq = new LinkedList<>();
        pq.add(new Node(cur, 0));
        dis[cur] = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();

            for(int next : lists[node.cur]) {
                if(dis[next] <= node.distance+1) continue;

                dis[next] = node.distance+1;
                pq.add(new Node(next, node.distance+1));
            }

        }
    }
}

class Node {
    int cur;
    int distance;

    public Node(int cur, int distance) {
        this.cur = cur;
        this.distance = distance;
    }
}