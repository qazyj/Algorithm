import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n+1];
        long MOD = 1000000007;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) arr[i] = Long.parseLong(st.nextToken());

        long[] prefixSum = new long[n+2];
        for (int i = n; i >= 1; i--) {
            prefixSum[i] += prefixSum[i+1] + arr[i];
        }

        long sum = 0l;
        for (int i = 1; i <= n; i++) {
            sum += arr[i] * prefixSum[i+1];
            sum %= MOD;
        }
        System.out.println(sum);
    }
}