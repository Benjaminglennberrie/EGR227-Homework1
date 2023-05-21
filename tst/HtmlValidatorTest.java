// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;
import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class HtmlValidatorTest {
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    private static final String EXPECTED_TEMPLATE = "expected_output/expected_output_for_test%d.txt";

    private static final String INPUT_TEMPLATE = "expected_output/test%d.html";

    private static void testAgainstFiles(int testNumber) {
        testValidatorWithFiles(String.format(EXPECTED_TEMPLATE, testNumber), String.format(INPUT_TEMPLATE, testNumber));
    }

    private static void myRemoveAllTests(int testNumber) {
        testValidatorWithFiles(String.format(EXPECTED_TEMPLATE, testNumber), String.format(INPUT_TEMPLATE, testNumber));
    }

    private static void testValidatorWithFiles(String expectedOutputFilePath, String validatorInputFilePath) {
        String rawInput = dumpFileContentsToString(validatorInputFilePath);
        String expected = dumpFileContentsToString(expectedOutputFilePath);
        HtmlValidator validator = new HtmlValidator(HtmlTag.tokenize(rawInput));

        String validatorOutput = captureValidatorOutput(validator);

        Assert.assertEquals("Validator output must match expected value", expected, validatorOutput);
    }

    private static String captureValidatorOutput(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        validator.validate();

        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    private static String dumpFileContentsToString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assert.fail("Could not load file: " + filePath);
            return null;
        }
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
    @Test
    public void test1(){
        testAgainstFiles(1);
    }

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
    @Test
    public void test2(){
        testAgainstFiles(2);
    }


    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
    @Test
    public void test3(){
        testAgainstFiles(3);
    }


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
    @Test
    public void test4(){
        testAgainstFiles(4);
    }

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
    @Test
    public void test5(){
        testAgainstFiles(5);
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
    @Test
    public void test6(){
        testAgainstFiles(6);
    }

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
    @Test
    public void test7(){
        testAgainstFiles(7);
    }

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
    @Test
    public void test8(){
        testAgainstFiles(8);
    }

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest1(){
        myRemoveAllTests(1);
    }

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest2(){
        myRemoveAllTests(2);
    }

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest3(){
        myRemoveAllTests(3);
    }

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest4(){
        myRemoveAllTests(4);
    }

    //FEEL FREE TO ADD MORE TESTS HERE
}
