import java.util.*;

class Solution {
    public int[] solution(String s) {
        Map<Integer, Integer> tempMap = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();

        String[] tempString = s.replace("{", "").replace("}", "").split(",");

        for (String string : tempString) {
            tempMap.compute(Integer.parseInt(string), (k, v) -> v == null ? 1 : v + 1);
        }
        
        tempMap.forEach((k, v) -> map.put(v, k));
        for(int i = 1; i <= map.size(); i++) {
        	System.out.print(map.get(i) + " ");
        }
        
        int n = map.size();
        int[] answer = new int[n];

        for (int i = 1; i <= n; i++) {
            answer[n-i] = map.get(i);
        }
        return answer;
    }
}