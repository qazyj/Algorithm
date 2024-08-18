class Solution {
    public int solution(int n, int k) {
        String s = Long.toString(n,k);
        String[] temp = s.split("0");

        int answer = 0;
        for(String cur : temp) {
            if(cur.length() == 0) continue;
            long value = Long.parseLong(cur);
            if(isPrime(value)) continue;

            answer++;
        }

        return answer;
    }

    public boolean isPrime(long n) {
        if (n == 1) return true;

        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return true;
        }
        return false;
    }
}