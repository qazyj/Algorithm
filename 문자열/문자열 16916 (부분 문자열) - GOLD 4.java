import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {
	private static char[] s;
	private static char[] p;

    static int[] getPi(String pattern) {
        int n = pattern.length();
        int[] pi = new int[n];
        char[] p = pattern.toCharArray();

        pi[0] = 0;
        int j = 0;
        for(int i = 1; i<n; i++) {
            while(j > 0 && p[i] != p[j]) {
                j = pi[j-1];
            }

            if(p[i] == p[j]) {
                pi[i] = j + 1;
                j++;
            }
        }
        return pi;
    }

    static int kmp (String source, String pattern) {
        int n = source.length();
        int m = pattern.length();
        int[] pi = getPi(pattern);

        int j = 0;
        for(int i = 0; i<n; i++) {
            while(j > 0 && s[i] != p[j]) {
                j = pi[j-1];
            }

            if(s[i] == p[j]) {
                if (j == m - 1) {
                    return 1;
                } else {
                    j++;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String source = br.readLine();
        s = source.toCharArray();
        String pattern = br.readLine();
        p = pattern.toCharArray();

        System.out.println(kmp(source, pattern));
    }
}