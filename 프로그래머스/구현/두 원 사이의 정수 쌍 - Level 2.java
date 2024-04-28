class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        for(long i = 1; i <= r2; i++) {
            int min = (int) Math.ceil(Math.sqrt(((long)r1*r1-i*i)));
            int max = (int) Math.floor(Math.sqrt(((long)r2*r2-i*i)));

            answer += (max-min+1);
        }

        return answer*4;
    }
}