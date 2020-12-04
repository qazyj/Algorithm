import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(ReturnWaitingTime());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N];	
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			array[i] = Integer.parseInt(st.nextToken());
		
		// 오름차순 정렬
		Arrays.sort(array);
	}
	
    private static int ReturnWaitingTime()
    {
    	int sum = 0;
				
		for(int i = 0; i < N; i++) {
			sum += array[i] * (N - i);
		}
    	
        return sum;
    }
}
