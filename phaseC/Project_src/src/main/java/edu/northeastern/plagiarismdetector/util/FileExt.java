package edu.northeastern.plagiarismdetector.util;

/**
 * Enum class FileExt defines in a central location different supported file extensions that would
 * otherwise be stored as strings in multiple individual methods.
 */
public enum FileExt {
  PY(".py"),
  CPP(".cpp"),
  H(".h");

  private final String ext;

  /**
   * Constructor method initializes and stores this enum's string representation.
   *
   * @param ext a string representing the file extension of this FileExt.
   */
  FileExt(String ext) {
    this.ext = ext;
  }

  /**
   * getMsg returns the string representation of this file extension.
   *
   * @return a String file extension.
   */
  public String getExt() {
    return this.ext;
  }
}