import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Algorithm {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
        int[] array = new int[10001];
        
        for (int i = 0; i < N; i++) 
            array[Integer.parseInt(br.readLine())] ++;
         
        for (int i = 1; i <= 10000; i++) {
            if (array[i] > 0) { 
                for (int j = 0; j < array[i]; j++) 
                    sb.append(i).append("\n");                
            }
        }
        System.out.print(sb);
	}
}