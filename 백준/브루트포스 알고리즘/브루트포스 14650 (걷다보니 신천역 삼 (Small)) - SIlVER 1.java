import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, count;

	public static void main(String[] args) throws Exception {
		SetData();
        Calculate(0, 0);
		System.out.println(count);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine());
        count = 0;
	}

    private static void Calculate(int n, int sum){
        for(int i = 0; i < 3; i++){
            if(n == 0 && i == 0){
                continue;
            }
            if(n == N){
                if(sum % 3 == 0) count++;
                return;
            } else {
                Calculate(n + 1, sum + i);
            }
        }
    }
}
