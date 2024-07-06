import java.util.*;

class Solution {
    static int[] dx = {-1,1,0,0}; // n,s,w,e
    static int[] dy = {0,0,-1,1};

    public int[] solution(String[] park, String[] routes) {
        int h = park.length;
        int w = park[0].length();
        char[][] maps = new char[h][w];
        int curX = 0;
        int curY = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                maps[i][j] = park[i].charAt(j);
                if(maps[i][j] != 'S') continue;
                curX = i;
                curY = j;
            }
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('N', 0);
        map.put('S', 1);
        map.put('W', 2);
        map.put('E', 3);

        for(int i = 0; i < routes.length; i++) {
            String[] temp = routes[i].split(" ");
            int direction = map.get(temp[0].charAt(0));
            int count = Integer.parseInt(temp[1]);

            int tX = curX;
            int tY = curY;
            boolean isGo = true;
            for(int j = 0; j < count; j++) {
                int r = tX + dx[direction];
                int c = tY + dy[direction];

                if(r < 0 || c < 0 || r >= h || c >= w || maps[r][c] == 'X') {
                    isGo = false;
                    break;
                }

                tX = r;
                tY = c;
            }

            if(!isGo) continue;
            curX = tX;
            curY = tY;
        }

        int[] answer = new int[]{curX,curY};
        return answer;
    }
}