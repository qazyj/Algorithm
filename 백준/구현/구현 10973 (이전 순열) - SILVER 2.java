import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {
	static int n;
	static int[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		if(nextPermutation(arr)) {		//이전 순열 존재 여부 확인
			for(int i=0;i<n;i++) {
				sb.append(arr[i] + " ");		//존재시 이전 순열 BufferedWriter 저장
			}
		}else
			System.out.println(-1);
		System.out.println(sb);
	}

	private static boolean nextPermutation(int[] arr) {
		int i = arr.length-1;
		while(i > 0 && arr[i-1] <= arr[i]) i--;
		if(i <= 0) return false;

		int j = arr.length-1;

		while(arr[j] >= arr[i-1]) j--;

		swap(arr, i-1, j);
		j = arr.length - 1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		return true;
	}

	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
}