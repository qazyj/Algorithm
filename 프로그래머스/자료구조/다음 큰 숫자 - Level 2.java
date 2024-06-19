class Solution {
    public int solution(int n) {
        int answer = 0;
        int count = countOnes(Integer.toBinaryString(n));
        while(true) {
            n++;
            int c = countOnes(Integer.toBinaryString(n));
            if(count == c) {
                answer = n;
                break;
            }
        }
        return answer;
    }

    public int countOnes(String binaryString) {
        int count = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}