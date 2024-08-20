class Solution {
    public int solution(int[] ingredient) {
        int[] stack = new int[ingredient.length];
        int index = 0;
        int answer = 0;
        for (int i : ingredient) {
            stack[index++] = i;
            if (index >= 4 && stack[index - 1] == 1
                    && stack[index - 2] == 3
                    && stack[index - 3] == 2
                    && stack[index - 4] == 1) {
                index -= 4;
                answer++;
            }
        }
        return answer;
    }
}