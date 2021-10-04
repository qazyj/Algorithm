class Solution {
    public int solution(String s) {
        int length = s.length();
        int answer = length;

        for (int i = 1; i <= length/2; i++) {
            String prev = s.substring(0, i);
            int count = 1;
            String result = "";
            String temp = "";

            for (int j = i; j < length; j += i) {
                if (j + i > s.length()) {
                    temp = s.substring(j);
                    continue;
                }
                if (prev.equals(s.substring(j, j + i))) {
                    count++;
                } else {
                    result += prev;
                    if (count != 1) {
                        result = count + result;
                    }
                    prev = s.substring(j, j + i);
                    count = 1;
                }
            }
            result += prev + temp;
            if (count != 1) {
                result = count + result;
            }

            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}