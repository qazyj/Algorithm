import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        char[] c1 = str1.toLowerCase().toCharArray();
        char[] c2 = str2.toLowerCase().toCharArray();
        double comb = 0;
        for(int i = 1; i < c1.length; i++) {
            if(!Character.isLetter(c1[i-1]) || !Character.isLetter(c1[i])) continue;
            String t = c1[i-1]+""+c1[i];
            map1.put(t, map1.getOrDefault(t, 0)+1);
            comb++;
        }

        for(int i = 1; i < c2.length; i++) {
            if(!Character.isLetter(c2[i-1]) || !Character.isLetter(c2[i])) continue;
            String t = c2[i-1]+""+c2[i];
            map2.put(t, map2.getOrDefault(t, 0)+1);
            comb++;
        }

        double inter = 0;
        for (String key : map1.keySet()) {
            if(!map2.containsKey(key)) continue;

            inter += Math.min(map1.get(key), map2.get(key));
        }
        comb -= inter;
        if(comb == 0) return 65536;
        return (int)(inter/comb*65536);
    }
}