import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[][] arr = new int[r][c];
        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] sumArray = new int[r][c];
        sumArray[0][0] = arr[0][0];
        for(int i = 1; i < c; i++) {
            sumArray[0][i] = sumArray[0][i-1] + arr[0][i];
        }

        for(int i = 1; i < r; i++) {
            sumArray[i][0] = sumArray[i-1][0] + arr[i][0];
        }

        for(int i = 1; i < r; i++) {
            for(int j =1; j < c; j++) {
                sumArray[i][j] = sumArray[i-1][j] + sumArray[i][j-1] - sumArray[i-1][j-1] + arr[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;

            int total = (r2-r1+1)*(c2-c1+1);
            int sum = 0;
            if(r1 > 0) {
                sum += sumArray[r1-1][c2];
            }

            if(c1 > 0) {
                sum += sumArray[r2][c1-1];
            }

            if(r1 > 0 && c1 > 0) {
                sum -= sumArray[r1-1][c1-1];
            }

            sb.append((sumArray[r2][c2]-sum)/total).append("\n");
        }

        System.out.println(sb);
    }
}