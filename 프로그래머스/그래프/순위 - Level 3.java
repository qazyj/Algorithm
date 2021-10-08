class Solution {
    public int solution (int n, int[][] results){
        boolean[][] game =  new boolean[n+1][n+1];

        for(int i=0; i<results.length; i++)
            game[results[i][0]][results[i][1]]=true;


        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                for(int k=1; k<=n; k++){
                    if(game[i][j]&&game[i][k]){game[j][k]=true;}
                }
            }
        }

        int answer = 0;
        for(int i=1; i<=n; i++){
            int result=0;
            for(int j=1; j<=n; j++){
                if(game[i][j] || game[j][i])
                    result++;
            }

            if(result==n-1)
                answer++;
        }

        return answer;
    }
}