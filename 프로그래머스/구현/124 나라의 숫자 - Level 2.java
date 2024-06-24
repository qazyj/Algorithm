class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        String[] numbers = {"4", "1", "2"};

        while(n>0) {
            answer.append(numbers[n%3]);
            n = (n-1)/3;
        }

        return answer.reverse().toString();
    }
}