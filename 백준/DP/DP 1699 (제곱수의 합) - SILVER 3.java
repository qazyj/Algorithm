import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int N;
	static long[] array;

	public static void main(String[] args) throws Exception {
		SetData();
        Calculate();
		System.out.println(array[N] % 1000000009);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine());
        array = new long[33334];
	}

    private static void Calculate(){
    	array[1] = 0;
        array[2] = 2;        

        for (int i = 3; i <= N; i++)
                 array[i] = (array[i - 1] * 3) % 1000000009;
    }
}