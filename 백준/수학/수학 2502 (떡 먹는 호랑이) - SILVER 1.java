import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] array = new int[30][2];
		array[0][0] = array[1][1] = 1;
		array[0][1] = array[1][0] = 0;
		
		for(int i=2; i<array.length; i++) {
			array[i][0] = array[i-1][0] + array[i-2][0];
			array[i][1] = array[i-1][1] + array[i-2][1];
		}
		
		int x = array[d-1][0];
		int y = array[d-1][1];
		int second = k/y;
		int first = 0;
		boolean check = false;
		
		while(!check) {
			first = k - (second * y);
			if(first%x==0) {
				first /= x;
				check = true;
			}else {
				second--;
			}
		}
		
		System.out.println(first);
		System.out.println(second);
		
	}

}