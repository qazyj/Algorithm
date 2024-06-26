import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        Set<String> set = new HashSet<>();
        set.add(words[0]);

        for(int i = 1; i < words.length; i++) {
            String cur = words[i - 1];
            String next = words[i];

            char last = cur.charAt(cur.length() - 1);
            char first = next.charAt(0);

            if(set.contains(next) || last != first) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;

                return answer;
            }

            set.add(words[i]);
        }
        return answer;
    }
}