import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long count;
	static long[] number, temp;

	public static void main(String[] args) throws Exception {
		SetData();
		Sort(0, N-1);
		System.out.println(count);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		number = new long[N];
		temp = new long[N];
		count = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			number[i] = Long.parseLong(st.nextToken());
	}

	public static void Sort(int start, int end) {
		if(start < end) {
			int middle = (start+end)/2;
			Sort(start, middle);
			Sort(middle+1, end);
			merge(start,middle,end);
		}
	}

	private static void merge(int start, int middle, int end) {
		int startToMidStartIndex = start;
		int midToEndStartIndex = middle+1;
		int startTempIndex = start;
	
		while(startToMidStartIndex<=middle && midToEndStartIndex<=end) {
			if(number[startToMidStartIndex] <= number[midToEndStartIndex]) {
				temp[startTempIndex++] = number[startToMidStartIndex++];
			}else {
				temp[startTempIndex++] = number[midToEndStartIndex++];
				count +=  (middle + 1 - startToMidStartIndex);
			}
		}
		while(startToMidStartIndex<=middle)
			temp[startTempIndex++] = number[startToMidStartIndex++];
		while(midToEndStartIndex<=end)
			temp[startTempIndex++] = number[midToEndStartIndex++];
		
		for(int k=start; k<=end; k++) {
			number[k] = temp[k];
		}
		
	}
}
