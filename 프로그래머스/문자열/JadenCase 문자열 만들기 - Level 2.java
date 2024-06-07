class Solution {
    public String solution(String s) {
        String[] temp = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < temp.length; i++) {
            if(temp[i].length() == 0) {
                sb.append(" ");
                continue;
            }
            sb.append(temp[i].substring(0,1).toUpperCase());
            sb.append(temp[i].substring(1, temp[i].length()).toLowerCase());
            sb.append(" ");
        }
        if(s.charAt(s.length()-1) == ' '){
            return sb.toString();
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}