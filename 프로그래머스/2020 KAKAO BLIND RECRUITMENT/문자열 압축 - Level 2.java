public class Solution {
    public int solution(String s) {
      int answer = s.length();

      for (int i = 1; i <= s.length() / 2; i++) {
        int compLength = compression(s, i).length();
        answer = Math.min(answer, compLength);
      }

      return answer;
    }

    private String compression(String string, int i) {

      int count = 1;
      String compression = "";
      String pattern = "";

      for (int j = 0; j <= string.length() + i; j += i) {

        String nowString;

        // 전 문자열과 비교할 현재 문자열
        if (j >= string.length()) { // 현재 문자열이 없을 때
          nowString = "";
        } else if (string.length() < j + i) { // 마지막 현재 문자열일 때
          nowString = string.substring(j);
        } else {
          nowString = string.substring(j, j + i);
        }

        // 1. 전 문자열이랑 똑같은지 비교한다. (맨 처음이면 비교 X)
        if (j != 0) {
          if (nowString.equals(pattern)) { // 같으면
            count++;
          } else if (count >= 2) { // 다르고 count가 2회 이상
            compression += count + pattern;
            count = 1;
          } else { // 압축 불가능
            compression += pattern;
          }
        }
        pattern = nowString;
      }

      return compression;
    }
}