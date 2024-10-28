import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int answer = 1;
        int pre = arr[0];
        for(int i = 1; i < n; i++) {
            if(pre+l <= arr[i]) {
                pre = arr[i];
                answer++;
            }
        }
        System.out.println(answer);
    }
}