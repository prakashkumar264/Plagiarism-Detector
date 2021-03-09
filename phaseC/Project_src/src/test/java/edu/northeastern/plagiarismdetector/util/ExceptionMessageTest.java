package edu.northeastern.plagiarismdetector.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ExceptionMessageTest class tests the ExceptionMessage Enums.
 */
public class ExceptionMessageTest {

  @Test
  public void testINVALID_FILE_PATH() {
    ExceptionMessage message = ExceptionMessage.INVALID_FILE_PATH;
    String exp = "Invalid File Path!";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testFILE_ERROR() {
    ExceptionMessage message = ExceptionMessage.FILE_ERROR;
    String exp = "Error reading: ";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testCOULD_NOT_STORE_FILE() {
    ExceptionMessage message = ExceptionMessage.COULD_NOT_STORE_FILE;
    String exp = " Could not store file. Please try again.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_INIT_ROOT_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_INIT_ROOT_DIR;
    String exp = "Error initializing root dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_INIT_PKG1_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_INIT_PKG1_DIR;
    String exp = "Error initializing pkg1 dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_INIT_PKG2_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_INIT_PKG2_DIR;
    String exp = "Error initializing pkg2 dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_DEL_ROOT_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_DEL_ROOT_DIR;
    String exp = "Error deleting root dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_DEL_PKG1_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_DEL_PKG1_DIR;
    String exp = "Error deleting pkg1 dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_DEL_PKG2_DIR() {
    ExceptionMessage message = ExceptionMessage.ERR_DEL_PKG2_DIR;
    String exp = "Error deleting pkg2 dir for temp file storage.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_DEL_FILE() {
    ExceptionMessage message = ExceptionMessage.ERR_DEL_FILE;
    String exp = "Error deleting temp file.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testUNSUPPORTED_LANG() {
    ExceptionMessage message = ExceptionMessage.UNSUPPORTED_LANG;
    String exp = "Unsupported Language Selection";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testUNSUPPORTED_FILE_EXT() {
    ExceptionMessage message = ExceptionMessage.UNSUPPORTED_FILE_EXT;
    String exp = "Unsupported File Extension";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_ANTLR_FILE_PARSE() {
    ExceptionMessage message = ExceptionMessage.ERR_ANTLR_FILE_PARSE;
    String exp = "Error parsing file.";

    assertEquals(exp, message.getMsg());
  }

  @Test
  public void testERR_GET_LINES() {
    ExceptionMessage message = ExceptionMessage.ERR_GET_LINES;
    String exp = "Error getting lines.";

    assertEquals(exp, message.getMsg());
  }

}