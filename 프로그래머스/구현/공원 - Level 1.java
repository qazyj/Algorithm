import java.util.*;

class Solution {
    static int n,m;

    public int solution(int[] mats, String[][] park) {
        int answer = 0;

        n = park.length;
        m = park[0].length;

        int max = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!park[i][j].equals("-1")) continue;

                max = Math.max(max, dfs(park, i, j));
            }
        }

        for(int i = 0; i < mats.length; i++) {
            if(max < mats[i]) continue;

            answer = Math.max(answer, mats[i]);
        }

        if(answer == 0) return -1;
        return answer;
    }

    public int dfs(String[][] park, int i, int j) {
        int v = 1;
        while(true) {
            if(i+v >=n || j+v >= m) break;

            boolean flag = false;
            for(int a = i; a < i+v; a++) {
                if(park[a][j+v].equals("-1")) continue;

                flag = true;
                break;
            }
            if(flag) break;

            for(int a = j; a < j+v; a++) {
                if(park[i+v][a].equals("-1")) continue;

                flag = true;
                break;
            }
            if(flag) break;

            v++;
        }

        return v;
    }
}