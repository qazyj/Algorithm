class Solution {
    public long solution(int n) {
        long[] answer = new long[n+2];
        answer[1] = 1;
        answer[2] = 2;
        for(int i = 3; i <= n; i++) {
            answer[i] = (answer[i-1]+answer[i-2])%1234567;
        }
        return answer[n];
    }
}