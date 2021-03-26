import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> preBuildingNumber = new ArrayList<>();
 
        for (int i = 0; i <= N; i++) {
            preBuildingNumber.add(new ArrayList<>());
        }
 
        int[] indegree = new int[N + 1]; // 특정 건물을 짓기 전에 먼저 지어야 할 건물의 개수
        int[] times = new int[N + 1]; // 특정 건물을 짓는 데 걸리는 시간
 
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
 
            times[i] = Integer.parseInt(st.nextToken());	// 해당 건물을 짓기 위한 시간
            while (true) {
                int preBuildingNumberTemp = Integer.parseInt(st.nextToken());
 
                if (preBuildingNumberTemp == -1)
                    break;
 
                preBuildingNumber.get(preBuildingNumberTemp).add(i);
 
                indegree[i]++;		// 해당 건물을 짓기 전 지어야 할 건물의 수를 더해준다.
            }
        }
 
        String timeForBuilding = topologicalSort(preBuildingNumber, indegree, times, N);
 
        System.out.print(timeForBuilding);

    }
    
    // 위상 정렬
    public static String topologicalSort(ArrayList<ArrayList<Integer>> a, int[] indegree, int[] times, int N) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        // 먼저 지어야할 건물이 없는 건물을 큐에 집어 넣음. 해당 큐에 있는 건물을 먼저 짓는다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 특정 건물을 짓기 전까지 걸린 시간 누적
        int[] result = new int[N + 1];
 
        while (!queue.isEmpty()) {
            int now = queue.poll();
 
            for (int next : a.get(now)) {
                indegree[next]--;
                
                // next 건물을 짓기 전까지 걸린 시간 계산.
                result[next] = Math.max(result[next], result[now] + times[now]);
 
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // 특정 건물을 짓기 전 먼저 지어야할 건물의 시간 + 특정 건물을 짓는 시간
        for (int i = 1; i <= N; i++) {
            sb.append((result[i] + times[i]) + "\n");
        }
 
        return sb.toString();
    }
}