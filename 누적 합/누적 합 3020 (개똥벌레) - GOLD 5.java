import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[H];
        int[] up = new int[H];

        for(int i = 0;i<N;i+=2){
            down[Integer.parseInt(br.readLine()) - 1]++;
            up[Integer.parseInt(br.readLine()) - 1]++;
        }

        int[] downTotal = new int[H];
        int[] upTotal = new int[H];

        downTotal[H - 1] = down[H - 1];
        upTotal[0] = up[H - 1];

        for (int i = H - 2; i >= 0; i--) {
        	downTotal[i] = downTotal[i + 1] + down[i];
        }
        for (int i = 1; i < H; i++) {
        	upTotal[i] = upTotal[i - 1] + up[H - i - 1];        
        }

        int min = 1000000000;
        for (int i = 0; i < H; i++) {
            if(min >= downTotal[i] + upTotal[i])
            	min = downTotal[i] + upTotal[i];
        }
        int count = 0;

        for (int i = 0; i < H; i++) {
            if (min == downTotal[i] + upTotal[i]) count++;
        }

        System.out.println(min+" "+count);
    }
        
}