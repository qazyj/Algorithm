import java.util.*;

class Solution {
    static boolean[] primes;
    static boolean[] visited;
    static Set<Integer> set;

    public int solution(String numbers) {
        set = new HashSet<>();
        primes = new boolean[10000001];
        primes[0] = true;
        primes[1] = true;
        for(int i = 2; i < 10000001; i++) {
            for(int j = i+i; j < 10000001; j+=i) {
                if(primes[j]) continue;

                primes[j] = true;
            }
        }

        visited = new boolean[numbers.length()];
        backtracking(numbers, 0, "");

        return set.size();
    }

    public void backtracking(String number, int depth, String s) {
        if(depth == number.length()) {
            if(s.equals("")) return;
            int realNumber = Integer.parseInt(s);
            if(primes[realNumber]) return;
            if(set.contains(realNumber)) return;

            set.add(realNumber);
            return;
        }

        backtracking(number, depth+1, s);
        for(int i = 0; i < number.length(); i++) {
            if(visited[i]) continue;

            visited[i] = true;
            backtracking(number, depth+1, s+number.charAt(i));
            visited[i] = false;
        }
    }
}