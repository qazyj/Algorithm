import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] array = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			array[i] = Integer.parseInt(st.nextToken());

		int start = 0, end = 0, sum = 0, answer=N+1,length=0;

		while (true) { 
			if (sum >= S) { 
				answer = Math.min(length, answer);
				sum -= array[start++];
				length--;
			} else if(end >= N) { 
				break;
			}
			else { 
				sum += array[end++];
				length++;
			}

		}

		if (answer==N+1) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}
}