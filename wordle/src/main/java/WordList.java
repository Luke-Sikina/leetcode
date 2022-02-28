import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WordList {
    private static final String PATH = "words_alpha.txt";
    private static WordList instance;
    private final Map<Character, Set<String>> wordMap = new HashMap<>();

    private WordList() throws IOException, NullPointerException {
        ClassLoader classLoader = WordList.class.getClassLoader();
        File file = new File(classLoader.getResource(PATH).getFile());
        var words = Arrays.stream(FileUtils.readFileToString(file, "UTF-8")
            .split("\n"))
            .map(String::trim)
            .toList();

        words.forEach(word -> {
                word.chars().boxed()
                    .map(i -> (char)i.intValue())
                    .distinct()
                    .forEach(c -> {
                        if (wordMap.containsKey(c)) {
                            Set<String> wordsForChar = wordMap.get(c);
                            wordsForChar.add(word);
                        } else {
                            wordMap.put(c, new HashSet<>(Set.of(word)));
                        }
                    });
            });
    }


    public synchronized static WordList getInstance() throws IOException {
        if (instance == null) {
            instance = new WordList();
        }

        return instance;
    }

    public Set<String> getAllWordsExcludingCharSet(Set<Character> chars) {
        return wordMap.entrySet().stream()
            .filter(e -> !chars.contains(e.getKey()))
            .flatMap(e -> e.getValue().stream())
            .filter(word -> word.chars().boxed().map(i -> (char)i.intValue()).noneMatch(chars::contains))
            .collect(Collectors.toSet());
    }

    public Set<String> getAllWordsForCharSet(Set<Character> chars) {
        return chars.stream()
            .map(wordMap::get)
            .reduce(
                wordMap.get(chars.iterator().next()),
                (cur, acc) -> acc.stream().filter(cur::contains).collect(Collectors.toSet())
            );
    }
}
