import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection {

    public void findIntersectionInStrings(String fileName) {
        String separator = File.separator;
        String resourcesDirectory = "src" + separator + "main" + separator + "resources";
        String absoluteFilePath = resourcesDirectory + separator + fileName;

        List<String> listOfStrings = readFile(absoluteFilePath);

        if (listOfStrings.size() > 1)
            intersectionOfTwoStrings(listOfStrings.get(0), listOfStrings.get(1));
        else
            System.out.println("Amount of strings in file less than two.");
    }

    private List<String> readFile(String fileName) {
        List<String> listOfStrings = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            listOfStrings = stream
                    .filter(line -> !line.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfStrings;
    }

    private void initHashMap(Map<Character, Integer> hashMap, String str) {
        for (char ch : str.toCharArray()) {
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }
    }

    private Map<Character, Integer> sortInDescendingOrder(Map<Character, Integer> hashMap) {
        return hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void intersectionOfTwoStrings(String str1, String str2) {
        Map<Character, Integer> hashMap1 = new HashMap<>(Math.min(str1.length(), 255));
        Map<Character, Integer> hashMap2 = new HashMap<>(Math.min(str2.length(), 255));

        initHashMap(hashMap1, str1);
        initHashMap(hashMap2, str2);

        // deleting from hashMap all not repeated symbols
        hashMap1.keySet().retainAll(hashMap2.keySet());

        // sum up values from first and second hashMaps
        hashMap1.forEach((character, integer) ->
                hashMap1.put(character, hashMap1.get(character) + hashMap2.get(character)));

        Map<Character, Integer> sorted = sortInDescendingOrder(hashMap1);
        System.out.println(sorted.keySet());
    }
}
