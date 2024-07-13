class Solution {
    public int solution(int[] numbers) {
        boolean[] visited = new boolean[10];
        for(int i = 0; i < numbers.length; i++) {
            visited[numbers[i]] = true;
        }
        int answer = 0;
        for(int i = 1; i < 10; i++) {
            if(visited[i]) continue;

            answer += i;
        }
        return answer;
    }
}