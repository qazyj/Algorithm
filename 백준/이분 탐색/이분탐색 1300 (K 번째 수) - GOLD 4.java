import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static long N;
	private static long k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Long.parseLong(br.readLine());
		k = Long.parseLong(br.readLine());
		
		System.out.print(bSearch(1,k));
	}

    private static long bSearch(long left, long right) {
    	if(left > right) return left;
        int count = 0;
        long mid = (left + right) / 2;
        for(int i = 1; i <= N; i++) {
            count += Math.min(mid/i, N);
        }
        
        if(k <= count) {
            return bSearch(left, mid -1);
        }else{
            return bSearch(mid + 1, right);
        }
    }
}