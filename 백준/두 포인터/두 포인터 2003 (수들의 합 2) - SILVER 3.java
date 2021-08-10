import java.io.*;
import java.util.*;

public class Algorithm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int index = Integer.parseInt(s);
        if(index > 1022){
            System.out.println(-1);
            System.exit(0);
        }
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<10; i++){
            DecreaseNumber(i, 1, list);
        }

        Collections.sort(list);


        System.out.println(list.get(index));
    }

    static ArrayList DecreaseNumber(long num, int temp, ArrayList list){
        if(temp > 10){
            return list;
        }

        list.add(num);

        for(int i=0; i<10; i++){
            if(num%10 > i){
                DecreaseNumber((num*10)+i, temp+1, list);
            }
        }
        return list;
    }
}