import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        
        while((s=br.readLine())!=null) {
        	s = s.trim();
            int n = Integer.parseInt(s);
            int[] array = new int[n+1];
            int[] LIS = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }
            LIS[0] = array[0];
            int count = 0;
            for(int i=1;i<=n;i++) {
                int idx = binary(LIS, 1,count+1,array[i]);
                LIS[idx] = array[i];
                if(idx == count+1) count++;
            }
            System.out.println(count);
        }
    }
    private static int binary(int[] LIS, int startIndex,int endIndex,int key) {
        while(startIndex<endIndex) {
            int mid = (startIndex+endIndex) /2;
            if(LIS[mid]<key) startIndex = mid + 1;
            else endIndex = mid;
        }
        return endIndex;
    }
}