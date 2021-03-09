package edu.northeastern.plagiarismdetector.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FileExtTest class tests the FileExt Enums.
 */
class FileExtTest {

  @Test
  public void testPY() {
    FileExt ext = FileExt.PY;
    String exp = ".py";

    assertEquals(exp, ext.getExt());
  }

  @Test
  public void testCPP() {
    FileExt ext = FileExt.CPP;
    String exp = ".cpp";

    assertEquals(exp, ext.getExt());
  }

  @Test
  public void testH() {
    FileExt ext = FileExt.H;
    String exp = ".h";

    assertEquals(exp, ext.getExt());
  }
}