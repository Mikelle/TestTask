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
        intersection.findIntersectionInStrings("test.txt");

        String expected = "[a,  , m, p, !, я, 2, 4]\n";
        assertThat(outputStream.toString(), is(expected));
    }

    @Test
    public void testEmptyFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Intersection intersection = new Intersection();
        intersection.findIntersectionInStrings("emptyFile.txt");

        String expected = "Amount of strings in file less than two.\n";
        assertThat(outputStream.toString(), is(expected));
    }
}
