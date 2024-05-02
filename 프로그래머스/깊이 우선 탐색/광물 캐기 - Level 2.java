import java.util.*;

class Solution {
    static int answer;
    static int[] picks;
    static String[] minerals;
    static int[][] dig = new int[][]{{1,1,1}, {5,1,1}, {25,5,1}};
    static Map<String, Integer> maps;

    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        this.picks = picks;
        this.minerals = minerals;
        maps = new HashMap<>();
        maps.put("diamond", 0);
        maps.put("iron", 1);
        maps.put("stone", 2);
        dfs(0, 0, 0, 0);
        return answer;
    }

    public void dfs(int index, int sum, int count, int use) {
        if(index == minerals.length || (count == 0 && isEmpty())) {
            answer = Math.min(answer, sum);
            return;
        }

        if(count > 0) {
            dfs(index+1, sum+dig[use][maps.get(minerals[index])], count-1, use);
        } else {
            for(int i = 0; i < 3; i++) {
                if(picks[i] == 0) continue;

                picks[i]--;
                dfs(index+1, sum+dig[i][maps.get(minerals[index])], 4, i);
                picks[i]++;
            }
        }
    }

    public boolean isEmpty() {
        for(int i = 0; i < 3; i++) {
            if(picks[i] != 0) return false;
        }
        return true;
    }
}