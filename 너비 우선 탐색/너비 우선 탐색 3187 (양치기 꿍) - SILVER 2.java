import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int R,C, sheep, wolf;
	static char[][] array;
	static boolean[][] check;
    static int[] x;
    static int[] y;

	public static void main(String[] args) throws Exception {
		SetData();
		bfs(0,0);
		System.out.println(sheep + " " + wolf);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		check = new boolean[R][C];
		array = new char[R][C];
		x = new int[]{-1, 0, 1, 0};
		y = new int[]{0, -1, 0, 1};
		sheep = wolf = 0;
		
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                array[i][j] = line.charAt(j);
            }
        }
		
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(!check[i][j] && array[i][j] != '#')
                	bfs(i,j);
            }
        }
	}
	
	private static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {a,b});
     	check[a][b] = true;
        int wolfCnt = 0;
        int sheepCnt = 0;
        while (!queue.isEmpty()) {
        	int location[] = queue.poll();
            int r = location[0];
            int c = location[1];
            if (array[r][c] == 'k') {
                sheepCnt++;
            } else if (array[r][c] == 'v') {
                wolfCnt++;
            }
            for (int i = 0; i < 4; i++) {
                int r2 = r + x[i];
                int c2 = c + y[i];
                if(r2 < 0 || r2 >= R || c2 < 0 || c2 >= C) continue;
                
                if (!check[r2][c2] && array[r2][c2] != '#') {
                    check[r2][c2] = true;
                    queue.offer(new int[] {r2,c2});
                }
            }
        }
        if (sheepCnt > wolfCnt) {
            sheep += sheepCnt;
        } else {
            wolf += wolfCnt;
        }
	}
}
