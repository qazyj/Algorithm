class Solution {
    public int solution(String s) {
        int answer = 1;

        char c = s.charAt(0);
        int temp = 1;
        for (int i = 1; i < s.length(); i++) {
            if (temp == 0) {
                answer++;
                c = s.charAt(i);
            }

            if (c == s.charAt(i)) {
                temp++;
            } else {
                temp--;
            }
        }

        return answer;
    }
}