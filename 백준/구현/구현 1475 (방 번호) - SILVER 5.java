import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int[] arr = new int[10];
		for(char c : s.toCharArray()){
			int v = c-'0';
			arr[v]++;
		}
		arr[6] += (arr[9]+1);
		arr[6] /= 2;
		int max = 0;
		for(int i = 0; i <= 8; i++) max = Math.max(max, arr[i]);
		System.out.println(max);
	}

}