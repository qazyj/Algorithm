class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        char[] ca = s.toCharArray();
        int index = 0;
        for(char c : ca) {
            if(c == ' ') {
                index = 0;
                sb.append(c);
                continue;
            }

            if(index % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
            index++;
        }

        return sb.toString();
    }
}