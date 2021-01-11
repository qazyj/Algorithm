import java.io.*;
import java.util.*;

public class Algorithm {
	static int[][] array;
	static int[] check;
	static int answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		array = new int[10][10];
		check = new int[5];
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		if(answer == Integer.MAX_VALUE)
			answer = -1;
	}

	private static void dfs(int y) {
        if(CheckSuccess()) {
            int count = 0;
            for(int i = 0 ; i < 5 ; i++) {
                count += check[i];
            }
            if(answer > count)
                answer = count;
            return;
        }
 
        for(int i = y ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                if(array[i][j] == 1) {
                    for(int Size = 4 ; Size >=0 ; Size--) {
                        if(check[Size] < 5 && CheckSquare(i, j, Size)) {
                            check[Size]++;
                            CoverSquare(i, j, Size);
                            dfs(i);
                            UncoverSquare(i, j, Size);
                            check[Size]--;
                        }
                    }
                    return;
                }
            }
        }
	}
	
	private static boolean CheckSquare(int y, int x, int Size) {
		// 규격을 벗어나는 경우
		if(x + Size >= 10 || y + Size >= 10) return false;
		
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                if(array[i][j] == 0)
                    return false;
            }
        }
        return true;
	}
	
	private static void CoverSquare(int y, int x, int Size) {	
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                array[i][j] = 0;
            }
        }
	}
	
	private static void UncoverSquare(int y, int x, int Size) {		
        for(int i = y ; i <= Size + y ; i++) {
            for(int j = x ; j <= Size + x; j++) {
                array[i][j] = 1;
            }
        }
	}
	
    private static boolean CheckSuccess() {
        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                if(array[i][j] == 1)
                    return false;
            }
        }
        return true;
    }
}