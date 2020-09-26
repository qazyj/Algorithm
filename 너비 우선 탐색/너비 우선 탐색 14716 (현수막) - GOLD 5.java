import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] histogram = {2, 2, 2, 3};
        int answer = 0, x = 0, y = histogram.length-1;
        
        while(x<y) {
            answer = Math.max(((y-x)-1)*Math.min(histogram[x],histogram[y]),answer);
            if(((y-1-x)-1)*Math.min(histogram[x],histogram[y-1]) > answer) y--;
            else if (((y-x+1)-1)*Math.min(histogram[x+1],histogram[y]) > answer) x++;
            else x++;
        }
		System.out.println(answer);
	}
	
	
}
