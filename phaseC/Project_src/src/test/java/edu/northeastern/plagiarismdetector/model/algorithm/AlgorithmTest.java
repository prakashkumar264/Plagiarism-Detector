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
 * Test class for Algorithm class.
 */
public class AlgorithmTest {

  private static Properties p;
  private static String pythonHelloworld1;
  private static String pythonHelloworld2;
  private static String cppHelloworld1;
  private static String cppHelloworld2;
  private static String pythonSox18;

  @BeforeClass
  public static void before() {
    p = new Properties();
    try {
      p.load(new FileReader(new File("./src/test/resources/application.properties")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    pythonHelloworld1 = p.getProperty("python.helloworld");
    pythonHelloworld2 = p.getProperty("python.helloworld2");
    cppHelloworld1 = p.getProperty("cpp.helloworld");
    cppHelloworld2 = p.getProperty("cpp.helloworld2");
    pythonSox18 = p.getProperty("python.sox18");
  }

  //test HashMap of basic Python helloworld program
  @Test
  public void calculateHash1() throws Exception {
    FileHandler getfile = new FileHandler();
    ParserRuleContext parsedfile = getfile.parsePython(pythonHelloworld1);
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedfile, "Python");
    HashMap actualMap = ast.getHashMap();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 1677);
    }};
    assertEquals(expectedMap, actualMap);
  }

  //test HashMap of second Python helloworld program
  @Test
  public void calculateHash2() throws Exception {
    FileHandler getfile = new FileHandler();
    ParserRuleContext parsedfile = getfile.parsePython(pythonHelloworld2);
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedfile, "Python");
    HashMap actualMap = ast.getHashMap();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 1677);
    }};
    assertEquals(expectedMap, actualMap);
  }

  //test HashMap of first C++ helloworld program
  @Test
  public void calculateHash3() throws Exception {
    FileHandler getfile = new FileHandler();
    ParserRuleContext parsedfile = getfile.parseCPP(cppHelloworld1);
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedfile, "C++");
    HashMap actualMap = ast.getHashMap();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 19646);
      put(4, 12284);
      put(5, 5692);
      put(6, 41541);
      put(7, 2470);
    }};
    assertEquals(expectedMap, actualMap);
  }

  //test HashMap of second C++ helloworld program
  @Test
  public void calculateHash4() throws Exception {
    FileHandler getfile = new FileHandler();
    ParserRuleContext parsedfile = getfile.parseCPP(cppHelloworld2);
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedfile, "C++");
    HashMap actualMap = ast.getHashMap();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(2, 21602);
      put(4, 14240);
      put(5, 7648);
      put(6, 45316);
      put(7, 19463);
      put(8, 2470);
    }};
    assertEquals(expectedMap, actualMap);
  }

  //test HashMap of longer Python program
  @Test
  public void calculateHash5() throws Exception {
    FileHandler getfile = new FileHandler();
    ParserRuleContext parsedfile = getfile.parsePython(pythonSox18);
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedfile, "Python");
    HashMap actualMap = ast.getHashMap();
    Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>() {{
      put(1, 4456);
      put(11, 22317);
      put(12, 979);
      put(13, 979);
      put(14, 946);
      put(15, 1133);
      put(16, 957);
      put(17, 1122);
      put(18, 979);
      put(19, 1122);
      put(20, 1122);
      put(21, 1133);
      put(22, 957);
      put(23, 979);
      put(24, 979);
      put(25, 1144);
      put(26, 649);
      put(28, 11593);
      put(29, 823);
      put(30, 793);
      put(31, 905);
      put(32, 774);
      put(33, 921);
      put(34, 631);
      put(37, 497);
      put(38, 713);
      put(39, 1077);
      put(40, 1456);
      put(41, 820);
      put(42, 926);
      put(43, 1147);
      put(44, 1307);
      put(47, 1588);
      put(50, 899);
      put(51, 498);
      put(53, 610);
      put(54, 1078);
      put(58, 714);
      put(61, 658);
      put(62, 1457);
      put(65, 609);
      put(66, 821);
      put(70, 658);
      put(71, 927);
      put(74, 1660);
      put(77, 1308);
      put(80, 3640);
      put(83, 3741);
      put(84, 4750);
      put(85, 3392);
      put(86, 3190);
      put(87, 4301);
      put(88, 4202);
      put(90, 421);
    }};
    assertEquals(expectedMap, actualMap);
  }

}