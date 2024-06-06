class Solution {
    public long solution(long k, long d) {
        long answer = 0;

        for(long x = 0; x <= d; x += k){
            long max = (long) Math.sqrt(d*d - x*x);
            answer += max / k + 1;
        }

        return answer;
    }
}