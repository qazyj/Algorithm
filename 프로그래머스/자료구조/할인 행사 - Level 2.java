import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        int[] count = new int[want.length];
        for(int i = 0; i < want.length; i++) {
            map.put(want[i], i);
        }

        for(int i = 0; i < 10; i++) {
            if(!map.containsKey(discount[i])) continue;

            count[map.get(discount[i])]++;
        }

        boolean check = false;
        for(int j = 0; j < number.length; j++) {
            if(count[j] < number[j]) check = true;
        }
        if(!check) answer++;

        for(int i = 10; i < discount.length; i++) {
            if(map.containsKey(discount[i-10])) {
                count[map.get(discount[i-10])]--;
            }

            if(map.containsKey(discount[i])) {
                count[map.get(discount[i])]++;
            }

            check = false;
            for(int j = 0; j < number.length; j++) {
                if(count[j] < number[j]) check = true;
            }
            if(check) continue;
            answer++;
        }




        return answer;
    }
}