import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] t = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            t[i] = (numbers[i]+"");
        }
        Arrays.sort(t,(t1, t2) -> (t2+t1).compareTo(t1+t2));
        if(t[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numbers.length; i++) {
            //System.out.println(t[i]);
            sb.append(t[i]);
        }
        return sb.toString();
    }
}