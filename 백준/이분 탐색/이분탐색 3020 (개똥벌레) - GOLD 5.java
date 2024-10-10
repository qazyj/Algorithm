import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] ups = new int[N/2];
		int[] downs = new int[N/2];
		for(int i=0;i<N;i++){
			if(i%2==0) downs[i/2] = Integer.parseInt(br.readLine());
			else ups[i/2] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(ups);
		Arrays.sort(downs);

		int min = Integer.MAX_VALUE;
		int num = 0;
		for(int i=1;i<=H;i++){
			int down = calc(0,N/2,downs,i);
			int up = calc(0,N/2,ups,H-i+1);

			if(min > down+up){
				min = down+up;
				num = 1;
			}
			else if(min == down+up) num++;
		}

		System.out.println(min+" "+num);
	}

	static int calc(int left, int right, int[] arr, int h){
		while(left<right){
			int mid = (left+right)/2;

			if(arr[mid] >= h) right = mid;
			else left = mid+1;
		}
		return arr.length-right;
	}
}