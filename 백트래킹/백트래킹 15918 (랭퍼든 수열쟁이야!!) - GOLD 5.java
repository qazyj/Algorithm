import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int[] array, check;
	static int n, x, y,count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		count = 0;
		
		array = new int[25];
		check = new int[25];
		array[x] = array[y] = y - x - 1;
		check[y-x-1] = 1;
		
		BackTracking(1);
		System.out.println(count);
	}

	private static void BackTracking(int index) {
		if(index == 2*n){
			count++; return;
		}
		if(array[index]==0){
			for(int i=1; i<=n; i++){
				if(check[i] == 1) continue;
				if(index+i+1<=2*n && array[index+i+1] == 0){
					array[index] = array[index+i+1] = i;
					check[i] = 1;
					BackTracking(index+1);
					array[index] = array[index+i+1] = check[i] = 0;
				}
			}
		}
	  else BackTracking(index+1);
	}
}
