class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, 0, target, 0);
        return answer;
    }

    private static void dfs(int[] numbers, int index, int target, int sum) {
        if(index == numbers.length){
            if(target == sum)
                answer++;
            return;
        }

        dfs(numbers, index+1, target, sum + numbers[index]);
        dfs(numbers, index+1, target, sum - numbers[index]);
    }
}