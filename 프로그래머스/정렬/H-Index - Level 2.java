import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        for(int i = 0; i < citations.length; i++) {
            int h_index = citations.length - i;

            if(citations[i] >= h_index) {
                answer = h_index;
                break;
            }
        }

        return answer;
    }
}