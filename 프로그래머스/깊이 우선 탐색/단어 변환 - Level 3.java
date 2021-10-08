class Solution {
    static int answer;
    static boolean[] check;

    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        check = new boolean[words.length];
        dfs(0, 0, begin, target, words);
        if(answer == Integer.MAX_VALUE)
            return 0;
        else
            return answer;
    }

    private static void dfs(int index, int count, String begin, String target, String[] words) {
        if(begin.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }

        for(int i = 0 ; i < words.length; i++) {
            if(check[i]) continue;
            int c = 0;
            for(int j = 0; j < words[i].length(); j++) {
                if(words[i].charAt(j) != begin.charAt(j))
                    c++;
            }
            check[i] = true;
            if(c == 1)
                dfs(index+1, count+1, words[i], target, words);
            check[i] = false;
        }
    }
}