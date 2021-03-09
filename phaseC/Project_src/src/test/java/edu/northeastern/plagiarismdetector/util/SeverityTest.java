package edu.northeastern.plagiarismdetector.util;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;

/**
 * Class SeverityTest tests the Severity Enum.
 */
public class SeverityTest {

  @Test
  public void testNoneGetLevel() {
    Assert.assertEquals(0, Severity.NONE.getLevel());
  }

  @Test
  public void testNoneGetName() {
    Assert.assertEquals("N/A", Severity.NONE.getName());
  }

  @Test
  public void testNoneGetColorObj() {
    Assert.assertEquals(null, Severity.NONE.getColorObj());
  }

  @Test
  public void testNoneGetHex() {
    Assert.assertEquals("#00ffffff", Severity.NONE.getHex());
  }

  @Test
  public void testLowGetLevel() {
    Assert.assertEquals(1, Severity.LOW.getLevel());
  }

  @Test
  public void testLowGetName() {
    Assert.assertEquals("LOW", Severity.LOW.getName());
  }

  @Test
  public void testLowGetColorObj() {
    Assert.assertEquals(Color.YELLOW, Severity.LOW.getColorObj());
  }

  @Test
  public void testLowGetHex() {
    Assert.assertEquals("#ffff00", Severity.LOW.getHex());
  }

  @Test
  public void testMediumGetLevel() {
    Assert.assertEquals(2, Severity.MEDIUM.getLevel());
  }

  @Test
  public void testMediumGetName() {
    Assert.assertEquals("MEDIUM", Severity.MEDIUM.getName());
  }

  @Test
  public void testMediumGetColorObj() {
    Assert.assertEquals(Color.ORANGE, Severity.MEDIUM.getColorObj());
  }

  @Test
  public void testMediumGetHex() {
    Assert.assertEquals("#ffc800", Severity.MEDIUM.getHex());
  }

  @Test
  public void testHighGetLevel() {
    Assert.assertEquals(3, Severity.HIGH.getLevel());
  }

  @Test
  public void testHighGetName() {
    Assert.assertEquals("HIGH", Severity.HIGH.getName());
  }

  @Test
  public void testHighGetColorObj() {
    Assert.assertEquals(Color.RED, Severity.HIGH.getColorObj());
  }

  @Test
  public void testHighGetHex() {
    Assert.assertEquals("#ff0000", Severity.HIGH.getHex());
  }
}