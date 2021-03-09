package edu.northeastern.plagiarismdetector.util;

import java.awt.*;

/**
 * Enum class Severity defines in a central location the level, name, color object, and hexadecimal
 * color to represent the detected level potentially plagiarized code level of code.
 */
public enum Severity {
  NONE(0, "N/A", null),
  LOW(1, "LOW", Color.YELLOW),
  MEDIUM(2, "MEDIUM", Color.ORANGE),
  HIGH(3, "HIGH", Color.RED);

  private final int level;
  private final String name;
  private final Color color;
  private final String hex;

  /**
   * Constructor method initializes and stores this enum's representation.
   *
   * @param level int representing the numerical severity level, used by the plagiarism algorithm.
   * @param name  String representation of the plagiarism level.
   * @param color Color object representation of the plagiarism level.
   */
  Severity(int level, String name, Color color) {
    this.level = level;
    this.name = name;
    this.color = color;
    this.hex = convertColorHex(this.color);
  }

  /**
   * Method getLevel returns this enum's numerical severity.
   *
   * @return int representing the numerical severity level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Method getName returns this enum's severity level.
   *
   * @return String representing the string severity level.
   */
  public String getName() {
    return name;
  }

  /**
   * Method getColorObj returns this enum's severity level color representation. A severity of NONE
   * "N/A" returns null.
   *
   * @return Color object representing the severity level.
   */
  public Color getColorObj() {
    return color;
  }

  /**
   * Method getHex returns this enum's severity level color representation in hexadecimal format.
   * This is used for graphic display in the view. A severity of NONE "N/A" returns "#00ffffff which
   * is the value for white with 100% opacity.
   *
   * @return String hexadecimal color representation.
   */
  public String getHex() {
    return hex;
  }

  /**
   * Static Method convertColorHex converts the color's R, G, B values to a hexadecimal form. A null
   * color returns #00ffffff which is the value for white with 100% opacity.
   *
   * @param color Color object to be converted.
   * @return String hexadecimal color representation.
   */
  private static String convertColorHex(Color color) {
    if (color == null) return "#00ffffff";
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }
}