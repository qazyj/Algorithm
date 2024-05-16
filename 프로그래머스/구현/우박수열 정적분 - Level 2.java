import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Integer> list = new ArrayList<>();
        list.add(k);

        while(k > 1) {
            if(k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            list.add(k);
        }

        for(int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = list.size() - 1 + ranges[i][1];

            if(start > end) {
                answer[i] = -1;
                continue;
            }

            double sum = 0;
            for(int j = start; j < end; j++) {
                double left = list.get(j);
                double right = list.get(j + 1);
                sum += (left + right) / 2;
            }
            answer[i] = sum;
        }
        return answer;
    }
}