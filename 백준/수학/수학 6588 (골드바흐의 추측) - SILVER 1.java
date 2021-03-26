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
		
		check = new boolean[1000001];
        getPrimeNumber();
        sb = new StringBuilder();
        
        while(true) {
            int number = Integer.parseInt(br.readLine());
            if(number == 0) break;
            
            for(int i = 1; i <= number/2; i++) {
                int index1 = i;		// 소수 1
                int index2 = number - i;	// 소수 2
                if(!check[index1] && !check[index2]) {		// 둘 다 소수가 맞으
                    sb.append(number + " = " +index1 + " + " + index2 + "\n");
                    break;
                }
            }
        }
	}
	
    public static void getPrimeNumber() {
		check[0] = check[1] = true;
		for (int i = 2; i <= 1000000; i++) {
			if (check[i] == true) {
				continue;
			}
			// 해당 수로 나누어 떨어지는 수는 소수이므로 true로 check
			for (int j = i + i; j <= 1000000; j+=i) {
				check[j] = true;
			}
		}
    }

}