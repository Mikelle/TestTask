import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntersectionTest {

    @Test
    public void testIntersection() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Intersection intersection = new Intersection();
        String path = Paths.get("src", "main", "resources", "test.txt").toString();

        intersection.findIntersectionInStringsInFile(path);

        String expected = String.format("[a,  , m, p, !, я, 2, 4]%n");
        assertThat(outputStream.toString(), is(expected));
    }

    @Test
    public void testEmptyFile() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Intersection intersection = new Intersection();
        String path = Paths.get("src", "main", "resources", "emptyFile.txt").toString();

        intersection.findIntersectionInStringsInFile(path);

        String expected = String.format("Amount of not empty strings in file less than two. Please, give correct file%n");
        assertThat(outputStream.toString(), is(expected));
    }
}