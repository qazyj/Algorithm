import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static String ppap;
	static int count;

	public static void main(String[] args) throws Exception {
		SetData();
		SearchStringPPAP();
		System.out.println(ppap);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ppap = br.readLine();
		count = 0;
	}
	
    public static void SearchStringPPAP() {
    	for(int i = 0; i < ppap.length(); i++) {
    		if(ppap.charAt(i) == 'P') {
    			count++;
    		} else {
    			// PPAP이면 PPAP는 P로 바꿀 수 있기 때문에 count를 -1만 해준다.
    			if(count >= 2 && ppap.length() > i + 1 && ppap.charAt(i+1) == 'P') {
    				count--;
    				i++;
    			} else {	// 현재 A인데 앞에 PP가 없으면 PPA가 되지 않기 때문에 NP를 출력
    				count = 0;
    				break;
    			}
    		}
    	}
    	
    	if(count == 1)
    		ppap = "PPAP";
    	else
    		ppap = "NP";
    }

}