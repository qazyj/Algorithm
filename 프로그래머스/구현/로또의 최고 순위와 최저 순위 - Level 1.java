import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : win_nums) {
            set.add(num);
        }
        int[] rank = new int[]{6,6,5,4,3,2,1};
        int zeroCount = 0;
        int correctCount = 0;
        for(int lotto : lottos) {
            if(set.contains(lotto)) correctCount++;
            if(lotto == 0) zeroCount++;
        }

        return new int[]{rank[correctCount+zeroCount], rank[correctCount]};
    }
}