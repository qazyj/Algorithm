import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int value = 0;
        
        for (int i = 0; i < N - 1; i++) {
            if (s.charAt(i) == 'E' && s.charAt(i+1) == 'W') {
            	value++;
            	i++;
            }
        }
        
        System.out.println(value);
    }

}
