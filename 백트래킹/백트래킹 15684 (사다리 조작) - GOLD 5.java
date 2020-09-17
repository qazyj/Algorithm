import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] array;
	static boolean check;
	static int minValue = 2100000000;
	static int N, M, H, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		array = new boolean[H+1][N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			array[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}

        for(int i=0; i<=3; i++) {
            result = i;
            ladder(1, 0);
            if(check) {
                System.out.print(result);
                System.exit(0);
            }
        }
        System.out.println("-1");
    }
	
    static void ladder(int y, int count) {
        if(count == result) {
            boolean possible = true;
            for(int i=1; i<=N; i++) {
                int col = i;
                for(int j=1; j<=H; j++) {
                    if(array[j][col])
                        col++;
                    else if(col>1 && array[j][col-1])
                        col--;
                }
                
                if(i!=col) {
                    possible = false;
                    break;
                }
            }
            if(possible)
            	check = true;
            return;
        }
        
        for(int i=y; i<=H; i++) {
            for(int j=1; j<N; j++) {
                if(!array[i][j]) {
                	array[i][j] = true;
                    ladder(i, count+1);
                    array[i][j] = false;
                }
            }
        }
        return;
    }
}
