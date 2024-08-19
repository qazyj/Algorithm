import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> parking = new HashMap<>();
        Map<Integer, Integer> money = new TreeMap<>();

        for(String record : records) {
            String[] temp = record.split(" ");
            int time = getTime(temp[0]);
            int number = Integer.parseInt(temp[1]);
            String move = temp[2];

            switch(move) {
                case "IN" -> parking.put(number, time);
                case "OUT" -> {
                    int preTime = parking.remove(number);
                    money.put(number, (money.getOrDefault(number, 0) + (time-preTime)));
                }
                default -> {}
            }
        }

        for(Integer key : parking.keySet()) {
            int preTime = parking.get(key);
            int time = getTime("23:59");

            money.put(key, money.getOrDefault(key, 0)+(time-preTime));
        }

        int[] answer = new int[money.size()];
        int index = 0;
        for (Integer key : money.keySet()) {
            answer[index++] = getPay(money.get(key), fees);
        }

        return answer;
    }

    public int getTime(String time) {
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
    }

    public int getPay(int time, int[] fees) {
        if(time <= fees[0]) return fees[1];

        time -= fees[0];
        int b = (int)Math.ceil(time/(double)fees[2]);

        return fees[1]+b*fees[3];
    }
}