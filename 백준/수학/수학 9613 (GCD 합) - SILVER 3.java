import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int countGCD = Integer.parseInt(br.readLine());
		
		for(int i=0;i<countGCD;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sum = 0;
			int arraySize = Integer.parseInt(st.nextToken());
			int[] array = new int[arraySize];
			
			for(int j=0;j<arraySize;j++)
				array[j] = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<arraySize-1;j++) 
				for(int z=j+1;z<arraySize;z++) 
					sum+= GCD(array[j],array[z]);

			sb.append(sum + "\n");
			
		}
		System.out.println(sb);		
    }
	
    private static int GCD(int x, int y)
    {
        if(x % y == 0) return y;
        return GCD(y, x%y);
    }

}
