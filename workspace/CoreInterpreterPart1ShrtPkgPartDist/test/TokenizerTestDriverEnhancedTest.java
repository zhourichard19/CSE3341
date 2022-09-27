import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.c3341.TokenizerTestEnhanced;

/**
 * JUnit test fixture for {@code edu.c3341.TestDriver}.
 */
public class TokenizerTestDriverEnhancedTest {

    /**
     * For capturing during a test case all output to the standard output
     * device.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * For remembering the original binding of the standard output device to the
     * console so as to restore that binding after each test.
     */
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(this.outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(this.originalOut);
    }

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private static String readFileFixedEncoding(String path)
            throws IOException {
        return readFile(path, StandardCharsets.UTF_8);
    }

    /**
     * The prefix of file paths.
     */
    private static final String PATH_PREFIX = "dataEnhanced/test";

    /**
     * Suffix of reference file's name, of expected output's name.
     */
    private static final String REF_SUFFIX = "expected-output";

    /*
     * Single point of control over changing the method under test.
     */
    private static void act(String[] args) {
        TokenizerTestEnhanced.main(args);
    }

    @Test
    public void test01E() throws IOException {
        final String tN = "01E";
        String[] args = { PATH_PREFIX + tN };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test02E() throws IOException {
        final String tN = "02E";
        String[] args = { PATH_PREFIX + tN };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test03E() throws IOException {
        final String tN = "03E";
        String[] args = { PATH_PREFIX + tN };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test12E() throws IOException {
        final String tN = "12E";
        String[] args = { PATH_PREFIX + tN };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

}
