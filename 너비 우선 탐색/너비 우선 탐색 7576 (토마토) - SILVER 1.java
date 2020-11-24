import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
    static int N, M, max;
    static int[][] array;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		SetData();
        bfs();

        if (!check()) {
            System.out.println(-1);
        } else if(max==0){
            System.out.println(max);
        }else{
            System.out.println(max-1);
        }
	}

	private static void SetData() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[M][N];
        max = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int M = Integer.parseInt(st.nextToken());
                array[i][j] = M;
                if (M == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
	}

    static public void bfs() {
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            for (int direction = 0; direction < 4; direction++) {
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];
                if (0 <= r && r < M && 0 <= c && c < N) {
                    if (array[r][c] == 0) {
                        array[r][c] = array[location[0]][location[1]] + 1;
                        queue.offer(new int[]{r, c});
                        if (array[r][c] > max) {
                            max = array[r][c];
                        }
                    }
                }
            }
        }
    }

    static public boolean check() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (array[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
