import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
    static int N, K;
    static int[] array, temp;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMinValue());
	}

	private static void SetData() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        array = new int[N+1];
        temp = new int[N-1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	array[i] = Integer.parseInt(st.nextToken());        	
        }
        
        for (int i = 0; i < N - 1; i++)
            temp[i] = array[i + 1] - array[i];
        
        Arrays.sort(temp);
	}

	private static int FindMinValue() {
		int min = 0;		
	    for (int i = 0; i < N - K; i++) min += temp[i];
		
		return min;
	}
}
