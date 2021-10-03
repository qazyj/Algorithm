class Solution {

    public String solution(int[][] scores) {
        String answer = "";

        for(int i = 0 ; i < scores.length; i++){
            double sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            boolean check = true;
            for(int j = 0; j < scores[i].length; j++){
                sum += scores[j][i];
                max = Math.max(max, scores[j][i]);
                min = Math.min(min, scores[j][i]);
                if(i != j && scores[j][i] == scores[i][i]) check = false;
            }

            if((max == scores[i][i] || min == scores[i][i]) && check) {
                sum = (sum - scores[i][i]) / (double)(scores.length-1);
                answer += GetGrade(sum);
                System.out.println(sum);
            } else {
                sum = sum / (double)scores.length;
                answer += GetGrade(sum);
                System.out.println(sum);
            }
        }

        return answer;
    }

    private static String GetGrade(double value) {
        if(value >= 90){
            return "A";
        } else if(value >= 80){
            return "B";
        } else if(value >= 70){
            return "C";
        } else if(value >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}