import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> words = new Solution().findWords();
    }

    private static final List<Character> alphabet = List.of(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    );
    public List<String> findWords() throws IOException {
        WordList wordList = WordList.getInstance();

        return createCharSetStream()
            .flatMap((set) -> {
                Set<String> words = wordList.getAllWordsForCharSet(set);
                if (!words.isEmpty()) {
                    return findWordsWithStart(List.of(words.iterator().next()), set, wordList);
                }

                return Stream.empty();
            })
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow();
    }

    private Stream<List<String>> findWordsWithStart(List<String> words, Set<Character> excludedChars, WordList wordList) {
        if (words.size() == 5) {
            return Stream.of(words);
        }

        Set<String> allWordsExcludingCharSet = wordList.getAllWordsExcludingCharSet(excludedChars);

        return allWordsExcludingCharSet.stream()
            .flatMap(word -> {
                Set<Character> chars = word.chars().boxed().map(i -> (char) i.intValue()).collect(Collectors.toSet());
                chars.addAll(excludedChars);
                List<String> wordsInner = new ArrayList<>(List.of(word));
                wordsInner.addAll(words);
                return findWordsWithStart(wordsInner, chars, wordList);
            });
    }

    private Stream<Set<Character>> createCharSetStream() {
        int ASCII_A = 'a';
        
        return Stream.iterate(0, i -> i + 1)
            .limit(26*25*24*23*22)
            .parallel()
            .map(i -> {
                int seed = i;
                char a = (char)((seed % 26) + ASCII_A);
                seed = seed / 26;
                char b = (char)((seed % 26) + ASCII_A + 1);
                seed = seed / 26;
                char c = (char)((seed % 26) + ASCII_A + 2);
                seed = seed / 26;
                char d = (char)((seed % 26) + ASCII_A + 3);
                seed = seed / 26;
                char e = (char)((seed % 26) + ASCII_A + 4);
                try {
                    return Set.of(a, b, c, d, e);
                } catch (IllegalArgumentException ex) {
                    return null;
                }
            })
            .filter(Objects::nonNull);
    }
}
