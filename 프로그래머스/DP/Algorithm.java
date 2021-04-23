import java.util.*;

class Solution {
    int n, goal, answer;
    
    public int solution(int N, int number) {
        answer = Integer.MAX_VALUE;
        n = N;
        goal = number;
        dfs(0, 0);
        
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    private void dfs(int value, int count) {
        if (count > 8) {
            answer = -1;
            return;
        }
        
        // basecase
        if (value == goal) {
            answer = Math.min(answer, count);
            return;
        }

        int temp = n;
        for (int i = 0; i < 8 - count; i++) {
            dfs(value + temp, count + i + 1);
            dfs(value - temp, count + i + 1);
            dfs(value / temp, count + i + 1);
            dfs(value * temp, count + i + 1);

            temp = temp * 10 + n;
        }
    }
}