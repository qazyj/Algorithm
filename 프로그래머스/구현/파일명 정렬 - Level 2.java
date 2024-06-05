import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        File[] temp = new File[files.length];

        int index = 0;
        for (String file : files) {
            String head = "";
            int num = 0;
            int idx = 0;

            for (int i = 0; i < file.length(); i++) {
                char cur = file.charAt(i);
                if (Character.isDigit(cur)) {
                    head = file.substring(0, i).toLowerCase();
                    idx = i;
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int j = idx; j < file.length(); j++) {
                char curj = file.charAt(j);
                if (Character.isDigit(curj)) {
                    sb.append(curj);
                } else break;
                if (sb.length() == 5) break;
            }
            num = Integer.parseInt(sb.toString());


            temp[index++] = new File(file, head, num);
        }

        Arrays.sort(temp, (o1, o2) -> {
            int res = o1.head.compareTo(o2.head);
            if (res == 0) {
                return o1.number - o2.number;
            }
            return res;
        });

        for(int i = 0; i < files.length; i++) {
            answer[i] = temp[i].name;
        }
        return answer;
    }
}

class File implements Comparable<File> {
    String name;
    String head;
    int number;

    public File(String name, String head, int number) {
        this.name = name;
        this.head = head;
        this.number = number;
    }

    @Override
    public int compareTo(File f) {
        if(this.head.compareTo(f.head) == 0) {
            return this.number - f.number;
        }
        return this.head.compareTo(f.head);
    }
}