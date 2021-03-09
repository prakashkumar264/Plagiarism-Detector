package edu.northeastern.plagiarismdetector.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * FileHandlerTest class tests the FileHandler class.
 */
public class FileHandlerUtilTest {

  @Test
  public void testInvalidPath1() {
    FileHandlerUtil handler = new FileHandlerUtil();

    try {
      handler.fileWalk("0");
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals("Invalid File Path!", e.getMessage());
    }
  }

  @Test
  public void testInvalidPath2() {
    FileHandlerUtil handler = new FileHandlerUtil();

    try {
      handler.fileWalk("");
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals("Invalid File Path!", e.getMessage());
    }
  }

  @Test
  public void testInvalidPath3() {
    FileHandlerUtil handler = new FileHandlerUtil();

    try {
      handler.fileWalk(null);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals("Invalid File Path!", e.getMessage());
    }
  }
}