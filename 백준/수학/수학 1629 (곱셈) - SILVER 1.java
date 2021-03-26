import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
 
        System.out.println(Calculation(a, b, c));
    }
 
    public static long Calculation(int a, int b, int c) {
        if (b == 0) return 1;       
        if (b % 2 == 1) {           
            return Calculation(a, b - 1, c) * a % c;
        } else {                    
            long v = Calculation(a, b / 2, c) % c;
            return v * v % c;
        }
    }
}
