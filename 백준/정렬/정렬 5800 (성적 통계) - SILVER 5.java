import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while(testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int gap = 0;
            for(int i = 1; i < n; i++) {
                gap = Math.max(arr[i]-arr[i-1], gap);
            }
            sb.append("Class " + index++).append("\n");
            sb.append("Max " + arr[n-1] + ", Min " + arr[0] + ", Largest gap " + gap).append("\n");
        }
        System.out.println(sb);
    }
}