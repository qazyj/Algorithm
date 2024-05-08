class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        long[] arr = new long[1001];
        for(int i = 0; i < weights.length; i++) {
            arr[weights[i]]++;
        }

        for(int i = 100; i <= 1000; i++) {
            if(arr[i] == 0) continue;

            answer += (arr[i]-1)*arr[i]/2;
            double divideByTwo = i/2.0;
            double divideByThree = i/3.0;
            if(divideByTwo*3 <= 1000) {
                if(isInteger(divideByTwo*3)) {
                    answer += (arr[i]*arr[(int)(divideByTwo*3)]);
                }
            }
            if(divideByTwo*4 <= 1000) {
                if(isInteger(divideByTwo*4)) {
                    answer += (arr[i]*arr[(int)(divideByTwo*4)]);
                }
            }
            if(divideByThree*4 <= 1000) {
                if(isInteger(divideByThree*4)) {
                    answer += (arr[i]*arr[(int)(divideByThree*4)]);
                }
            }
        }
        return answer;
    }

    public boolean isInteger(double num) {
        return Math.floor(num) == num;
    }
}