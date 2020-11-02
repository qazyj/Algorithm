import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] array = new long[69];
        array[0] = 1;
        array[1] = 1;
        array[2] = 2;
        array[3] = 4;
        for(int i = 4; i < 68; i++)
            array[i] = array[i - 1] + array[i - 2] + array[i - 3] + array[i - 4];
        
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(array[n] + "\n");
        }
    }
}