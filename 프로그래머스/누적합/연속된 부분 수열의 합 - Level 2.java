class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[]{0,1000001};
        int start = 0;
        int sum = sequence[0];
        if(sum == k) return new int[]{0,0};
        for(int i = 1; i < sequence.length; i++) {
            sum += sequence[i];

            if(sum > k) {
                while(sum > k) {
                    sum -= sequence[start++];
                }
            }
            if(sum == k) {
                if(i - start < answer[1]-answer[0]) {
                    answer[0] = start;
                    answer[1] = i;
                }
            }
        }
        return answer;
    }
}