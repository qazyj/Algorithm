import java.util.*;

class Solution {
    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n+1];
        PriorityQueue<Node> node = new PriorityQueue<>();
        for(int i = 0 ; i < costs.length; i++){
            node.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }

        for(int i = 0 ; i < n ; i++) parent[i] = i;

        while(!node.isEmpty()) {
            Node temp = node.poll();

            if(find(temp.start) == find(temp.end)) continue;
            else {
                union(temp.start, temp.end);
                answer += temp.cost;
            }
        }

        return answer;
    }

    public int find(int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB) parent[rootB] = rootA;
    }
}

class Node implements Comparable<Node> {
    int start;
    int end;
    int cost;

    public Node(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;

    }

    @Override
    public int compareTo(Node node){
        return this.cost - node.cost;
    }
}