import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection {

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
}
