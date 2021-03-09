package edu.northeastern.plagiarismdetector.util;

/**
 * Enum class SupportedLanguages defines in a central location different supported program languages
 * that otherwise be stored as strings in multiple individual methods.
 */
public enum SupportedLanguages {
  Python("Python"),
  CPP("C++");

  private final String language;

  /**
   * Constructor method initializes and stores this enum's string representation.
   *
   * @param lang a string representing a supported programming language..
   */
  SupportedLanguages(String lang) {
    this.language = lang;
  }

  /**
   * getLang returns the string representation of this programming language.
   *
   * @return a String programming language.
   */
  public String getLang() {
    return this.language;
  }
}