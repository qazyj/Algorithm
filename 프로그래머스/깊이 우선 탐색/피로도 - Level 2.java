import java.util.*;

class Solution {
    static boolean[] check;
    static int answer;

    public int solution(int k, int[][] dungeons) {
        check = new boolean[dungeons.length];
        answer = 0;

        for(int i = 0; i < dungeons.length; i++){
            if(dungeons[i][0] > k) continue;

            check[i] = true;
            dfs(k-dungeons[i][1], dungeons, 1);
            check[i] = false;
        }

        return answer;
    }

    private static void dfs(int k, int[][] dungeons, int count){
        answer = Math.max(answer, count);

        for(int i = 0; i < dungeons.length; i++){
            if(dungeons[i][0] > k || check[i]) continue;

            check[i] = true;
            dfs(k-dungeons[i][1], dungeons, count+1);
            check[i] = false;
        }
    }
}