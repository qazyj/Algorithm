import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	private static int N, K, answer;
	private static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		answer = 0;
		array = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			array[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(array);
		
        int[] temp = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            temp[i] = array[i + 1] - array[i];
        }
        Arrays.sort(temp);
 
        for (int i = 0; i < N - K; i++) 
            answer += temp[i];
        
	}
}
