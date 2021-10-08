import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> {
            return a[1] - b[1];
        });

        int temp = -30001;
        for(int i = 0 ; i < routes.length; i++) {
            if(routes[i][0] <= temp && temp <= routes[i][1]) continue;

            temp = routes[i][1];
            answer++;
        }
        return answer;
    }
}