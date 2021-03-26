import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int A, B, answer;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		getSum();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		answer = 0;
		
		int index = 0;
		array = new int[1001];
		for(int i = 1; i < 1001; i++) {
			for(int j = 0; j < i; j++) {
				array[index++] = i;
				if(index > 1000) break;
			}
			if(index > 1000) break;
		}
		
		
	}
	
    public static void getSum() {
		for (int i = A - 1; i <= B - 1; i++) {
			answer += array[i];
		}
    }
}
