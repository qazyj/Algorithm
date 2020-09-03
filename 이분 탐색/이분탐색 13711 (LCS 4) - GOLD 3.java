import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int input = Integer.parseInt(br.readLine());
        int[] LIS = new int[input + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= input; i++)
            LIS[Integer.parseInt(st.nextToken())] = i;
        
        int[] arrays = new int[input];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < input; i++)
            arrays[i] = LIS[Integer.parseInt(st.nextToken())];
        
        int j = 0;
        LIS[j++] = arrays[0];
        for (int i = 1; i < input; i++) {
            int index = Arrays.binarySearch(LIS, 0, j, arrays[i]);
            if (index == -(j + 1))
                LIS[j++] = arrays[i];
            else
                LIS[-(index + 1)] = arrays[i];
        }
        System.out.print(j);
	}
}
