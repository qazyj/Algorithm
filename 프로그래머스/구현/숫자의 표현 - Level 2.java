class Solution {
    public int solution(int n) {
        int answer = 0;
        int sum = 0;
        int left = 1;
        for(int i = 1; i <= n; i++) {
            sum += i;
            while(sum > n) {
                sum -= left;
                left++;
            }
            if(sum == n) answer++;
        }

        return answer;
    }
}