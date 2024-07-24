class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i += 2) {
            sb.append("수박");
        }
        if(n % 2 == 1) sb.append("수");
        return sb.toString();
    }
}