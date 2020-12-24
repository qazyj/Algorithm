import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static char[][] array;
	static boolean[] check;
	static StringBuilder sb;
	static int[] x = { -1, 0, 1, 0 };
	static int[] y = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		ArrayList<String> printingStart = new ArrayList<>();
        //N이 3일때 세팅
        printingStart.add("  *  ");
        printingStart.add(" * * ");
        printingStart.add("*****");
        
        //N => 3*2^k, 3, 6, 12, 24, 48, ... 이니깐 k가 1번째, 2번째 를 나타냄
        for(int k=1; 3 * (int)Math.pow(2, k) <= N; k++) { //6입력부터 루프 실행
            setStar(printingStart);
        }
        
        for(String s : printingStart){
            sb.append(s+ "\n");
        }
	}

	private static void setStar(ArrayList<String> printingStart){
        StringBuilder s = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        int size = printingStart.size();

        for(int i=0; i < size; i++){
            s.delete(0,s.length());

            //전 단계의 그림을 아래 하나, 옆 하나 이렇게 총 2개 복사
            s.append(printingStart.get(i)); //전 단계의 그림
            s.append(" ");      //공백
            s.append(printingStart.get(i)); //전 단계의 그림

            printingStart.add(s.toString());

            //전 단계 그림의 왼쪽, 오른쪽에 공백 추가
            s2.delete(0,s.length());
            for(int j=0; size>j; j++){
                s2.append(" ");
            }

            printingStart.set(i, s2.toString() + printingStart.get(i) + s2.toString());
        }
    }
}
