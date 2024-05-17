class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left)+1];
        int index = 0;
        for(long i = left; i <= right; i++) {
            long share = i / n+1;
            long rest = i % n+1;

            if(share >= rest) answer[index++] = (int)share;
            else answer[index++] = (int)rest;
        }
        return answer;
    }
}