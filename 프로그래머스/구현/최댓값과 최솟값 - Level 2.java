import java.util.*;

class Solution {
    public String solution(String s) {
        int[] temp = Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int min = temp[0];
        int max = temp[0];
        for (int i = 1; i < temp.length; i++) {
            if(min > temp[i]) min = temp[i];
            if(max < temp[i]) max = temp[i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min+ " " + max);

        return sb.toString();
    }
}