class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] temp = new int[]{0,7,6,5,4,3,2,1};
        int[][] scores = new int[4][8]; //{RT, CF, JM, AN}
        for(int i = 0; i < survey.length; i++) {
            switch(survey[i]) {
                case "RT" -> scores[0][choices[i]]++;
                case "TR" -> scores[0][temp[choices[i]]]++;
                case "FC" -> scores[1][temp[choices[i]]]++;
                case "CF" -> scores[1][choices[i]]++;
                case "MJ" -> scores[2][temp[choices[i]]]++;
                case "JM" -> scores[2][choices[i]]++;
                case "AN" -> scores[3][choices[i]]++;
                case "NA" -> scores[3][temp[choices[i]]]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        String[][] saveString = new String[][]{{"R","T"},{"C","F"},{"J","M"},{"A","N"}};
        int time = 0;
        for(int[] score : scores) {
            int sum = 0;
            int value = 1;
            for(int i = 3; i >= 1; i--) {
                sum -= (score[i]*value);
                value++;
            }

            value = 1;
            for(int i = 5; i < 8; i++) {
                sum += (score[i]*value);
                value++;
            }

            if(sum <= 0) {
                sb.append(saveString[time][0]);
            } else {
                sb.append(saveString[time][1]);
            }

            time++;
        }

        return sb.toString();
    }
}