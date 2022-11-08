package edu.c3341;

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

/**
 * JUnit test fixture for {@code edu.c3341.TestDriver}.
 */
public class CoreInterpreterTest {

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
    private static final String PATH_PREFIX = "data/test";

    /**
     * Suffix of reference file's name, of expected output's name.
     */
    private static final String REF_SUFFIX = "expected-output";

    /*
     * Single point of control over changing the method under test.
     */
    private static void act(String[] args) {
        CoreInterpreter.main(args);
    }

    @Test
    public void test05() throws IOException {
        final String tN = "05";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test06() throws IOException {
        final String tN = "06";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0701() throws IOException {
        final String tN = "07";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0702() throws IOException {
        final String tN = "07";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0703() throws IOException {
        final String tN = "07";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data03",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data03-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0704() throws IOException {
        final String tN = "07";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data04",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data04-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0801() throws IOException {
        final String tN = "08";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0802() throws IOException {
        final String tN = "08";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test0803() throws IOException {
        final String tN = "08";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data03",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data03-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test1401() throws IOException {
        final String tN = "14";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test1402() throws IOException {
        final String tN = "14";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02bad",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data02bad-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test1403() throws IOException {
        final String tN = "14";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data03",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data03-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test15() throws IOException {
        final String tN = "15";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test16() throws IOException {
        final String tN = "16";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test17() throws IOException {
        final String tN = "17";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test18() throws IOException {
        final String tN = "18";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test19() throws IOException {
        final String tN = "19";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test20() throws IOException {
        final String tN = "20";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test21() throws IOException {
        final String tN = "21";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test22() throws IOException {
        final String tN = "22";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test23() throws IOException {
        final String tN = "23";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test24a() throws IOException {
        final String tN = "24a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test24b() throws IOException {
        final String tN = "24b";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test25a() throws IOException {
        final String tN = "25a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test25b() throws IOException {
        final String tN = "25b";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26aNoPrint() throws IOException {
        final String tN = "26a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26a() throws IOException {
        final String tN = "26a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26b() throws IOException {
        final String tN = "26b";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26c() throws IOException {
        final String tN = "26c";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26d() throws IOException {
        final String tN = "26d";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26e() throws IOException {
        final String tN = "26e";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test26f() throws IOException {
        final String tN = "26f";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test27aNoPrint() throws IOException {
        final String tN = "27a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test27bNoPrint() throws IOException {
        final String tN = "27b";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test27a() throws IOException {
        final String tN = "27a";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test27b() throws IOException {
        final String tN = "27b";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test31() throws IOException {
        final String tN = "31";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test32() throws IOException {
        final String tN = "32";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3301() throws IOException {
        final String tN = "33";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3302() throws IOException {
        final String tN = "33";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3303() throws IOException {
        final String tN = "33";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data03",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-data03-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3401() throws IOException {
        final String tN = "34";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3402() throws IOException {
        final String tN = "34";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test3403() throws IOException {
        final String tN = "34";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data03",
                "doNotPrint" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-doNotPrint-data03-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test42() throws IOException {
        final String tN = "42";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test45() throws IOException {
        final String tN = "45";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test4601() throws IOException {
        final String tN = "46";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test4602() throws IOException {
        final String tN = "46";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test51() throws IOException {
        final String tN = "51";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test52() throws IOException {
        final String tN = "52";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test53() throws IOException {
        final String tN = "53";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test5401() throws IOException {
        final String tN = "54";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test5402() throws IOException {
        final String tN = "54";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data02",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data02-" + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test55() throws IOException {
        final String tN = "55";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test56() throws IOException {
        final String tN = "56";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test57() throws IOException {
        final String tN = "57";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test58() throws IOException {
        final String tN = "58";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test59() throws IOException {
        final String tN = "59";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test60() throws IOException {
        final String tN = "60";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + "07data01", "print" };
        act(args);
        assertEquals(readFileFixedEncoding(PATH_PREFIX + tN + REF_SUFFIX),
                this.outContent.toString());
    }

    @Test
    public void test6101() throws IOException {
        final String tN = "61";
        String[] args = { PATH_PREFIX + tN, PATH_PREFIX + tN + "data01",
                "print" };
        act(args);
        assertEquals(
                readFileFixedEncoding(
                        PATH_PREFIX + tN + "-print-data01-" + REF_SUFFIX),
                this.outContent.toString());
    }

}
