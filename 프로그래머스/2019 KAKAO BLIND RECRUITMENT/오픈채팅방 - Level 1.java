import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public String[] solution(String[] record) {
		ArrayList<String> c = new ArrayList<>();
		HashMap<String, String> name = new HashMap<String, String>();
		
		for(int i = 0; i < record.length; i++) {
			String[] splitRecord = record[i].split(" ");
			
			switch(splitRecord[0]) {
				case "Enter":
					name.put(splitRecord[1], splitRecord[2]);
				break;
				case "Change":
					name.put(splitRecord[1], splitRecord[2]);
					break;
			}
		}
		
		for(int i = 0; i < record.length; i++) {
			String[] splitRecord = record[i].split(" ");
			
			switch(splitRecord[0]) {
				case "Enter":
					c.add(name.get(splitRecord[1])+"님이 들어왔습니다.");
				break;
				case "Leave":
					c.add(name.get(splitRecord[1])+"님이 나갔습니다.");
					break;
			}
		}
		
		return c.toArray(new String[0]);
    }
}