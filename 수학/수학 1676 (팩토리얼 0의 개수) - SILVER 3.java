import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line;
        while ((line = br.readLine()) != null) {
            CompletableFuture.completedFuture(line)
                    .thenApply(XmlUtils::removeEscape)
                    .thenApply(XmlUtils::removeHex)
                    .thenApply(XmlUtils::checkAndRemoveTag)
                    .thenApply(XmlUtils::checkPlainText)
                    .thenApply(s-> "valid")
                    .exceptionally(s-> "invalid")
                    .thenAccept(System.out::println);
        }

    }
}

class XmlUtils {
    public static String removeEscape(String line) {
        return line.replaceAll("(&amp;)|(&lt;)|(&gt;)", "");
    }

    public static String removeHex(String line) {
        Matcher m = Pattern.compile("&x[a-fA-F0-9]+;").matcher(line);

        while (m.find()) {
            String hex = m.group().replaceAll("[&x;]", "");

            if (hex.length() % 2 != 0) {
                throw new RuntimeException();
            }
        }

        return line.replaceAll("&x[a-fA-F0-9]+;", "");
    }

    public static String checkAndRemoveTag(String line) {
        Stack<String> ctx = new Stack<>();

        line = line.replaceAll("<[a-z0-9]+/>", "");
        Matcher m = Pattern.compile("</?[a-z0-9]+>").matcher(line);

        while (m.find()) {
            String rawTag = m.group();

            if (rawTag.startsWith("</")) {
                String openTag = ctx.pop().replaceAll("[<>/]", "");
                String closeTag = rawTag.replaceAll("[<>/]", "");

                if (openTag.equals(closeTag) == false) {
                    throw new RuntimeException();
                }
            } else {
                ctx.push(rawTag);
            }
        }

        if (ctx.isEmpty() == false) {
            throw new RuntimeException();
        }

        return line.replaceAll("</?[a-z0-9]+>", "");
    }

    public static String checkPlainText(String line) {
        if ("invalid".equals(line) || line.contains("<") || line.contains(">") || line.contains("&")) {
            throw new RuntimeException();
        }

        for (char ch : line.toCharArray()) {
            if (ch < 32 || 127 < ch) {
                throw new RuntimeException();
            }
        }

        return line;
    }
}