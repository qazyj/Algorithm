import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if(map.containsKey(cur)) {
                answer[i] = i-map.get(cur);
            } else {
                answer[i] = -1;
            }
            map.put(cur, i);
        }

        return answer;
    }
}