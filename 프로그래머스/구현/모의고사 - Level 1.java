import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};

        int[] sums = new int[4];
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == one[i%one.length]) sums[1]++;
            if(answers[i] == two[i%two.length]) sums[2]++;
            if(answers[i] == three[i%three.length]) sums[3]++;
        }

        int max = Math.max(sums[1], Math.max(sums[2], sums[3]));
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            if(max != sums[i]) continue;

            list.add(i);
        }

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}