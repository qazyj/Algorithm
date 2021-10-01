import java.util.*;

class Solution {
    static int answer;
    public int solution(int[][] sizes) {
        int max = 0, min = 0;
        for(int i = 0 ; i < sizes.length; i++){
            max = Math.max(max, Math.max(sizes[i][0], sizes[i][1]));
            min = Math.max(min, Math.min(sizes[i][0], sizes[i][1]));
        }

        return max*min;
    }
}