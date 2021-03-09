package edu.northeastern.plagiarismdetector.util;


/**
 * Enum class ExceptionMessage defines in a central location different error messages for exception
 * handling that would otherwise be stored as strings in multiple individual methods.
 */
public enum ExceptionMessage {

  INVALID_FILE_PATH("Invalid File Path!"),
  FILE_ERROR("Error reading: "),
  COULD_NOT_STORE_FILE(" Could not store file. Please try again."),
  ERR_INIT_ROOT_DIR("Error initializing root dir for temp file storage."),
  ERR_INIT_PKG1_DIR("Error initializing pkg1 dir for temp file storage."),
  ERR_INIT_PKG2_DIR("Error initializing pkg2 dir for temp file storage."),
  ERR_DEL_ROOT_DIR("Error deleting root dir for temp file storage."),
  ERR_DEL_PKG1_DIR("Error deleting pkg1 dir for temp file storage."),
  ERR_DEL_PKG2_DIR("Error deleting pkg2 dir for temp file storage."),
  ERR_DEL_FILE("Error deleting temp file."),
  UNSUPPORTED_LANG("Unsupported Language Selection"),
  UNSUPPORTED_FILE_EXT("Unsupported File Extension"),
  ERR_ANTLR_FILE_PARSE("Error parsing file."),
  ERR_GET_LINES("Error getting lines.");

  private final String msg;

  /**
   * Constructor method initializes and stores this enum's string representation.
   *
   * @param message a string representing the error message of this exception.
   */
  ExceptionMessage(String message) {
    this.msg = message;
  }

  /**
   * getMsg returns the string representation of this exception's error message.
   *
   * @return a String error message.
   */
  public String getMsg() {
    return this.msg;
  }

}