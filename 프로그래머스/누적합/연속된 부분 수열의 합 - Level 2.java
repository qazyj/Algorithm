import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, ((o1, o2) -> o1[col - 1] != o2[col - 1] ? o1[col - 1] - o2[col - 1] : o2[0] - o1[0]));

        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int S_i = 0;
            for (int d : data[i]) S_i += (d % (i + 1));

            answer ^= S_i;
        }

        return answer;
    }
}