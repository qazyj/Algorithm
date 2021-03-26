import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(ReturnMaxMeetingRoom());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];	

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = o1[1] - o2[1];
                if (result != 0) return o1[1] - o2[1];	//종료시간에 따라 정렬
                return o1[0] - o2[0];		 //만약 비교하는 값의 종료시간이 같을 경우 시작시간으로 정렬
            }
        });
	}
	
    private static int ReturnMaxMeetingRoom()   {
        int count = 0;    // 최대값 변수 
        int temp = -1;    // 다음시작 시간과 비교할 변수
        for (int i = 0; i < N; i++) {
            //현재 시작시간이 이전 종료시간보다 늦을 경우 
            if (array[i][0] >= temp) {
                temp = array[i][1];
                count++;
            }
        }
        return count;
    }
}
