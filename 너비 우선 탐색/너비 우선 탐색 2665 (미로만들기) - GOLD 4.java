import java.io.*;
import java.util.*;

public class Algorithm {
    static int[][] array;
    static int[][] check;
    static int N;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        check = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                check[i][j]=2100000000;
            }
        }

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                array[i][j] = 1 - (input.charAt(j) - '0');
            }
        }
        bfs(0, 0);
        System.out.println(check[N-1][N-1]);
    }

    public static void bfs(int a, int b) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {a,b});
        check[0][0]=0;
        
        while(!queue.isEmpty()){
            int location[] = queue.poll();     

            for(int direction = 0; direction<4; direction++){
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];

                if(r >= 0 && c >= 0 && r < N && c < N) {
                    if(check[r][c] > check[location[0]][location[1]]+array[r][c]) {
                        check[r][c] = check[location[0]][location[1]]+array[r][c];
                        queue.add(new int[] {r,c});
                    }
                }
            }
        }
    }
}