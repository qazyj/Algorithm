import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] factorial = new long[N+1];
        factorial[0] = 1;
        factorial[1] = 1;
       
        for(int i=2; i<=N; i++) factorial[i] = (factorial[i-1]*i)%1000000007;
        long denominator = (factorial[K]*factorial[N-K])%1000000007;

        long index = 1000000007-2;
        long fermatNum = 1;
        while(index > 0){
            if(index%2==1){
                fermatNum *= denominator;
                fermatNum %= 1000000007;
            }
            denominator = (denominator*denominator)%1000000007;
            index /= 2;
        }
        long result = ((factorial[N]%1000000007)*(fermatNum%1000000007))%1000000007;
        System.out.print(result);

    }
}