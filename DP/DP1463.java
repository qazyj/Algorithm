import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
    	Scanner scanner = new Scanner(System.in);
        
    	int X = scanner.nextInt();     //정수 값 받아옴
    	int[] array = new int[X+1];	    //받아온 정수만큼 크기의 배열 생성
    	
    	for(int i = 2; i<=X;i++) {
    		if (i%3==0) 
    			array[i] = Math.min(array[i-1], array[i/3])+1;
    		else if (i%2==0) 
    			array[i] = Math.min(array[i-1],  array[i/2])+1;
    		else
    			array[i] = array[i-1] + 1;
    	}
    	System.out.println(array[X]);
		
	}
}
