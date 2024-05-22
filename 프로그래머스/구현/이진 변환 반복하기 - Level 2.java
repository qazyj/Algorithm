class Solution {
    public int[] solution(String s) {
        int count = 0;
        int zero = 0;
        while(!s.equals("1")){
            count++;
            int next = 0;
            for(int i=0; i<s.length();i++){
                if(s.charAt(i) == '0'){
                    zero++;
                } else{
                    next++;
                }
            }

            s = Integer.toBinaryString(next);
        }
        int[] answer = new int[] {count, zero};
        return answer;
    }
}