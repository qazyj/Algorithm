class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();

        char[] temp = s.toCharArray();

        for(char c : temp) {
            if(c != 32) {
                if(c <= 90) {
                    c += n;
                    if(c > 90) c -= 26;
                } else {
                    c += n;
                    if(c > 122) c -= 26;
                }
                sb.append(c);
            } else
                sb.append(" ");
        }

        return sb.toString();
    }
}