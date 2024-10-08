import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Long, Integer> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            long v = Long.parseLong(br.readLine());

            map.put(v, map.getOrDefault(v, 0) + 1);
        }

        Long answer = map.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.minBy(Comparator.comparing(Map.Entry::getKey))
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .flatMap(e -> e.getValue().map(Map.Entry::getKey))
                .orElse(null);
        System.out.println(answer);
    }
}