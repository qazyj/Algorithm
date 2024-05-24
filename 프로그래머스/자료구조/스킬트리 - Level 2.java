import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Set<Integer>[] set = new HashSet[26];
        for(int i = 0; i < 26; i++) {
            set[i] = new HashSet<>();
        }

        for(int i = 0; i < skill.length(); i++) {
            int index = skill.charAt(i)-'A';
            for(int j = 0; j < i; j++) {
                set[index].add(skill.charAt(j)-'A');
            }
        }

        int answer = 0;
        for(int i = 0; i < skill_trees.length; i++) {
            boolean[] isUsed = new boolean[26];
            boolean check = true;
            for(int j = 0; j < skill_trees[i].length(); j++) {
                int now = skill_trees[i].charAt(j)-'A';
                isUsed[now] = true;

                if(set[now].isEmpty()) continue;

                for(int mustUsed : set[now]) {
                    if(isUsed[mustUsed]) continue;

                    check = false;
                    break;
                }
                if(!check) break;
            }
            if(check) answer++;
        }
        return answer;
    }
}