class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        boolean isPlus = true;

        long purse1 = 0;
        long purse2 = 0;
        for (int num: sequence) {
            purse1 += isPlus ? num : -num;
            purse2 += isPlus ? -num : num;

            purse1 = Math.max(0, purse1);
            purse2 = Math.max(0, purse2);

            answer = Math.max(answer, Math.max(purse1, purse2));
            isPlus = !isPlus;
        }

        return answer;
    }
}