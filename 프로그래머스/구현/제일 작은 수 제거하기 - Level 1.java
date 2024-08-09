import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[]{-1};

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        int[] answer = new int[arr.length-1];
        int index = 0;
        for(int i = 0; i < arr.length; i++) {
            if(min == arr[i]) continue;

            answer[index++] = arr[i];
        }
        return answer;
    }
}