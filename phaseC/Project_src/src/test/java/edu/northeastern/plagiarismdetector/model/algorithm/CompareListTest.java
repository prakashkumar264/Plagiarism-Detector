package edu.northeastern.plagiarismdetector.model.algorithm;

import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for CompareList.
 */
public class CompareListTest {

  private static Properties p;
  private static String cpp_fraction;
  private static String cpp_fctswap;
  private static String cpp_loops;
  private static String cpp_renaming;
  private static String cpp_reorder;
  private static String cpp_same;
  private static String cpp_whitespace;
  private static String python_poly;
  private static String python_extract;
  private static String python_fctswap;
  private static String python_loops;
  private static String python_renaming;
  private static String python_reorder;
  private static String python_same;
  private static String python_whitespace;
  private static String cpp_helloworld1;
  private static String cpp_helloworld2;
  private static String python_helloworld1;
  private static String python_helloworld2;

  @BeforeClass
  public static void before() {
    p = new Properties();
    try {
      p.load(new FileReader(new File("./src/test/resources/application.properties")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    cpp_fctswap = p.getProperty("cpp.onefile_fctswap");
    cpp_fraction = p.getProperty("cpp.onefile_fraction");
    cpp_loops = p.getProperty("cpp.onefile_loops");
    cpp_renaming = p.getProperty("cpp.onefile_renaming");
    cpp_reorder = p.getProperty("cpp.onefile_reorder");
    cpp_same = p.getProperty("cpp.onefile_same");
    cpp_whitespace = p.getProperty("cpp.onefile_whitespace");
    python_poly = p.getProperty("python.onefile_poly");
    python_extract = p.getProperty("python.onefile_extract");
    python_fctswap = p.getProperty("python.onefile_fctswap");
    python_loops = p.getProperty("python.onefile_loops");
    python_renaming = p.getProperty("python.onefile_renaming");
    python_reorder = p.getProperty("python.onefile_reorder");
    python_same = p.getProperty("python.onefile_same");
    python_whitespace = p.getProperty("python.onefile_whitespace");
    cpp_helloworld1 = p.getProperty("cpp.helloworld");
    cpp_helloworld2 = p.getProperty("cpp.helloworld2");
    python_helloworld1 = p.getProperty("python.helloworld");
    python_helloworld2 = p.getProperty("python.helloworld2");
  }

  @Test
  public void comparePython() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_helloworld1);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_helloworld2);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    compareFiles.compareTrees();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 2);
    }};
    assertEquals(expectedMap, compareFiles.getCompareLines());
  }

  @Test
  public void compareCPP() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_helloworld1);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_helloworld2);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    compareFiles.compareTrees();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 2);
      put(4, 4);
      put(5, 5);
      put(6, 6);
      put(7, 7);
      put(8, 8);
    }};
    assertEquals(expectedMap, compareFiles.getCompareLines());
  }

  @Test
  public void testCPP1() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_fctswap);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testCPP2() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_loops);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testCPP3() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_renaming);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testCPP4() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_reorder);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testCPP5() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_same);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testCPP6() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parseCPP(cpp_fraction);
    ast.calculateHash(parsedfile1, "C++");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parseCPP(cpp_whitespace);
    ast.calculateHash(parsedfile2, "C++");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython2() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_fctswap);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython3() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_loops);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython4() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_renaming);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython5() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_reorder);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython6() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_same);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

  @Test
  public void testPython7() throws Exception {
    FileHandler getfile = new FileHandler();
    Algorithm ast = new Algorithm();
    ParserRuleContext parsedfile1 = getfile.parsePython(python_poly);
    ast.calculateHash(parsedfile1, "Python");
    HashMap actualMap1 = ast.getHashMap();
    ParserRuleContext parsedfile2 = getfile.parsePython(python_whitespace);
    ast.calculateHash(parsedfile2, "Python");
    HashMap actualMap2 = ast.getHashMap();
    CompareList compareFiles = new CompareList(actualMap1, actualMap2);
    assertEquals(100.0, compareFiles.compareTrees(), 0.1);
  }

}