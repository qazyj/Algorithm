class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < t*m; i++) {
            sb.append(Integer.toString(i,n).toUpperCase());
        }

        String temp = sb.toString();
        sb = new StringBuilder();
        for(int i = p-1; sb.length() < t; i += m) {
            sb.append(temp.charAt(i));
        }
        return sb.toString();
    }
}