import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        for(int i = 2; i <= 100000000; i++) {
            if(arrayA[0] % i == 0) {
                listA.add(i);
            }
            if(arrayB[0] % i == 0) {
                listB.add(i);
            }
        }

        int length = arrayA.length;
        for(int i = 1; i < length; i++) {
            List<Integer> removeA = new ArrayList<>();
            List<Integer> removeB = new ArrayList<>();
            for(int j = listA.size()-1; j >= 0; j--) {
                if(arrayA[i] % listA.get(j) == 0) continue;

                removeA.add(j);
            }

            for(int j = listB.size()-1; j >= 0; j--) {
                if(arrayB[i] % listB.get(j) == 0) continue;

                removeB.add(j);
            }

            for(int index : removeA) {
                listA.remove(index);
            }
            for(int index : removeB) {
                listB.remove(index);
            }
        }

        int answer = 0;
        for(int a : listA) {
            boolean check = false;
            for(int i = 0; i < length; i++) {
                if(arrayB[i] % a != 0) continue;

                check = true;
                break;
            }
            if(check) continue;

            answer = Math.max(a, answer);
        }

        for(int a : listB) {
            boolean check = false;
            for(int i = 0; i < length; i++) {
                if(arrayA[i] % a != 0) continue;

                check = true;
                break;
            }
            if(check) continue;

            answer = Math.max(a, answer);
        }

        return answer;
    }
}