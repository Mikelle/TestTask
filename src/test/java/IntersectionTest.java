import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntersectionTest {
    @Test
    public void testIntersection() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Intersection intersection = new Intersection();
        intersection.findIntersectionInStringsInFile("test.txt");

        String expected = OSUtils.isWindows() ? "[a,  , m, p, !, я, 2, 4]\r\n"
                                                : "[a,  , m, p, !, я, 2, 4]\n";
        assertThat(outputStream.toString(), is(expected));
    }

    @Test
    public void testEmptyFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Intersection intersection = new Intersection();
        intersection.findIntersectionInStringsInFile("emptyFile.txt");

        String expected = OSUtils.isWindows() ? "Amount of not empty strings in file less than two.\r\n"
                : "Amount of not empty strings in file less than two.\n";
        assertThat(outputStream.toString(), is(expected));
    }
}