import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, S;
	static int[] number;

	public static void main(String[] args) throws Exception {
		SetData();
		Sort();
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		number = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			number[i] = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(br.readLine());
	}

	private static void Sort() {
		for(int i = 0; i < N; i++) {
			int x = number[i];
			int y = i;
			int temp;
			
			// j-i <= S 해주는 이유 (시간) - j부터 i까지 최대 S번 바꿀 수 있는데 j-i가 S보다 크면 못 바꿈.
			for(int j = i + 1; j < N && j-i <= S; j++) {
				if(x < number[j]) {
					x = number[j];
					y = j;
				}	
			}
			
			// 현재 j-i중에 가장 큰 값을 그 전의 값과 바꿔주는 작업
			for(S -= (y-i); y > i; y--) {
				temp = number[y];
				number[y] = number[y-1];
				number[y-1] = temp;
			}
			
			if(S <= 0) break;		// S 가 0이하이면 바꿀 수 있는게 업서으니 반복문 나옴
		}

		for(int i = 0; i < N; i++)
			System.out.print(number[i] + " ");
	}
}
