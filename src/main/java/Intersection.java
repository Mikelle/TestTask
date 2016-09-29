import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();

        String path = Paths.get("src", "main", "resources", "test.txt").toString();

        if (args.length < 1)
            intersection.findIntersectionInStringsInFile(path);
        else
            intersection.findIntersectionInStringsInFile(args[0]);
    }

    public void findIntersectionInStringsInFile(String fileName) {
        List<String> listOfStrings = readFile(fileName);

        try {
            intersectionOfTwoStrings(listOfStrings.get(0), listOfStrings.get(1));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Amount of not empty strings in file less than two. Please, give correct file");
        }
    }

    private List<String> readFile(String fileName) {
        List<String> listOfStrings = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            listOfStrings = stream
                    .filter(line -> !line.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Can't find file. Please, specify correct name");
            System.exit(0);
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
                hashMap1.put(character, integer + hashMap2.get(character)));

        Map<Character, Integer> sorted = sortInDescendingOrder(hashMap1);
        System.out.println(sorted.keySet());
    }
}
