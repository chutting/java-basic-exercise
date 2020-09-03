import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner wordListInputScanner = new Scanner(System.in);
        String firstWordList = wordListInputScanner.next();
        String secondWordList = wordListInputScanner.next();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        if (!isStringLegal((firstWordList)) || !isStringLegal(secondWordList)) {
            throw new RuntimeException();
        }

        List<String> secondWordListAfterSplit = Arrays.stream(secondWordList.split(",")).collect(Collectors.toList());

        List<String> commonWordsWithSpaceList = Arrays.stream(firstWordList.split(","))
            .filter(s -> isListContainsStringIgnoreCase(secondWordListAfterSplit, s))
            .map(s -> s.toUpperCase())
            .distinct()
            .sorted()
            .map(s -> String.join(" ", s.split("")))
            .collect(Collectors.toList());

        return commonWordsWithSpaceList;
    }

    private static boolean isListContainsStringIgnoreCase(List<String> list, String str) {
        long containedStringIgnoreCaseCount = list.stream().filter(s -> s.equalsIgnoreCase(str)).count();
        return containedStringIgnoreCaseCount != 0;
    }

    private static boolean isStringLegal(String str) {
        long illegalWordCount = Stream.of(str.split(",")).filter(s -> s.isEmpty() || (!s.matches("^[a-zA-Z]+$"))).count();
        return illegalWordCount == 0;
    }
}
