package edu.northeastern.plagiarismdetector.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * SupportedLanguagesTest class tests the SupportedLanguages Enums.
 */
class SupportedLanguagesTest {

  @Test
  public void testPYTHON() {
    SupportedLanguages lang = SupportedLanguages.Python;
    String exp = "Python";

    assertEquals(exp, lang.getLang());
  }

  @Test
  public void testCPP() {
    SupportedLanguages lang = SupportedLanguages.CPP;
    String exp = "C++";

    assertEquals(exp, lang.getLang());
  }
}