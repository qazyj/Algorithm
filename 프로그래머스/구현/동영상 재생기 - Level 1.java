class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int total = getStringToInt(video_len);
        int cur = getStringToInt(pos);
        int s_op = getStringToInt(op_start);
        int e_op = getStringToInt(op_end);

        for(String command : commands) {
            if(cur >= s_op && cur <= e_op) cur = e_op;

            if(command.equals("next")) {
                cur += 10;
                if(cur > total) cur = total;
            } else if(command.equals("prev")) {
                cur -= 10;
                if(cur < 0) cur = 0;
            }

            if(cur >= s_op && cur <= e_op) cur = e_op;
        }


        return getIntToString(cur);
    }

    public int getStringToInt(String s) {
        String[] temp = s.split(":");
        return Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
    }

    public String getIntToString(int i) {
        int m = i/60;
        int s = i%60;
        return String.format("%02d", m) + ":"+String.format("%02d", s);
    }
}