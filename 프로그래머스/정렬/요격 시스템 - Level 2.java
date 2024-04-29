import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < targets.length; i++) {
            pq.add(new Node(targets[i][0], targets[i][1]));
        }

        double pre = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.x < pre && node.y > pre) continue;

            pre = node.y-0.1;
            answer++;
        }
        return answer;
    }
}

class Node implements Comparable<Node>{
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        return this.y - o.y;
    }
}

