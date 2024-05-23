import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1}; // D, U, L, R
    static int[] dy = {-1,1,0,0};

    public int solution(String dirs) {
        char[] arr = dirs.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put('D', 0);
        map.put('U', 1);
        map.put('L', 2);
        map.put('R', 3);

        List<Node> list = new ArrayList<>();
        int curX = 0;
        int curY = 0;
        for(int i = 0; i < arr.length; i++) {
            int dir = map.get(arr[i]);
            int nextX = curX + dx[dir];
            int nextY = curY + dy[dir];

            if(nextX < -5 || nextX > 5 || nextY < -5 || nextY > 5) continue;
            if(isVisited(list, curX, curY, nextX, nextY)) {
                curX = nextX;
                curY = nextY;
                continue;
            }

            list.add(new Node(curX, curY, nextX, nextY));
            curX = nextX;
            curY = nextY;
        }
        return list.size();
    }

    public boolean isVisited(List<Node> list, int curX, int curY, int nextX, int nextY) {
        for(Node n : list) {
            if(n.curX == curX && n.curY == curY && n.nextX == nextX && n.nextY == nextY) return true;
            if(n.nextX == curX && n.nextY == curY && n.curX == nextX && n.curY == nextY) return true;
        }
        return false;
    }
}


class Node {
    int curX;
    int curY;
    int nextX;
    int nextY;

    public Node(int curX, int curY, int nextX, int nextY) {
        this.curX = curX;
        this.curY = curY;
        this.nextX = nextX;
        this.nextY = nextY;
    }
}