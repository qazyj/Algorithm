import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int N;
	static boolean[] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine());
		check = new boolean[10001];
        getPrimeNumber();
        sb = new StringBuilder();
        
        for(int t=0; t < N; t++) {
            int number = Integer.parseInt(br.readLine());
            
            for(int i = number/2; i > 0; i--) {
                int index1 = i;		// 소수 1
                int index2 = number - i;	// 소수 2
                if(!check[index1] && !check[index2]) {		// 둘 다 소수가 맞으
                    sb.append(index1 + " " + index2 + "\n");
                    break;
                }
            }
        }
	}
	
    public static void getPrimeNumber() {
		check[0] = check[1] = true;
		for (int i = 2; i <= 10000; i++) {
			if (check[i] == true) {
				continue;
			}
			// 해당 수로 나누어 떨어지는 수는 소수이므로 true로 check
			for (int j = i + i; j <= 10000; j+=i) {
				check[j] = true;
			}
		}
    }

}
