class Solution {
    static int[] rates = new int[]{10,20,30,40};
    static int[] selectedRate;
    static int[] answer;
    static int[][] users;
    static int[] emoticons;

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        selectedRate = new int[emoticons.length];
        this.users = users;
        this.emoticons = emoticons;
        dfs(0);
        return answer;
    }

    public void dfs(int depth) {
        if(depth == selectedRate.length) {
            buyEmoticons();
            return;
        }

        for(int i = 0; i < 4; i++) {
            selectedRate[depth] = rates[i];
            dfs(depth+1);
        }
    }

    public void buyEmoticons() {
        int[] temp = new int[2];
        for(int[] user : users) {
            int curRate = user[0];
            int price = user[1];
            int money = 0;

            for(int i = 0; i < selectedRate.length; i++) {
                if(selectedRate[i] < curRate) continue;

                money += (emoticons[i] * (1-selectedRate[i]/100.0));
            }

            if(price <= money) temp[0]++;
            else temp[1] += money;
        }

        if(answer[0] < temp[0]) {
            answer[0] = temp[0];
            answer[1] = temp[1];
        } else if(answer[0] == temp[0]) {
            if(answer[1] < temp[1]) answer[1] = temp[1];
        }
    }
}