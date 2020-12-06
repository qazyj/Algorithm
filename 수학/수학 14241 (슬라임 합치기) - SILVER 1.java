import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(ReturnMaxMeetingRoom());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		array = new int[N];	

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
        //Arrays.sort(array);
	}
	
    private static int ReturnMaxMeetingRoom()   {
        int sum = 0, count = array[N-1];
        for(int i = N-1; i > 0; i--) {
        	sum += count*array[i-1];
        	count += array[i-1];
        }
        
        return sum;
    }
}
