package edu.northeastern.plagiarismdetector.model.algorithm;

import java.util.HashMap;

/**
 * This is one of the main algorithm classes. This runs the actual comparisons between two files.
 */
public class CompareList {

  // The initialization of hashed lines being passed in.
  private HashMap<Integer, Integer> firstHashedLines;
  private HashMap<Integer, Integer> secondHashedLines;

  // The similar lines between the two files.
  private HashMap<Integer, Integer> compareLines;

  /**
   * This is the constructor for the comparator object.
   *
   * @param firstHashedLines  the first file's hashed lines.
   * @param secondHashedLines the second file's hashed lines.
   */
  public CompareList(HashMap firstHashedLines, HashMap secondHashedLines) {
    this.firstHashedLines = firstHashedLines;
    this.secondHashedLines = secondHashedLines;
    this.compareLines = new HashMap<>();
  }

  /**
   * This method compares the two files based on their hashed line values and finds similar lines.
   * It then adds these lines to the HashMap compareLines.
   *
   * @return the percentage of plagiarism detected based on similar lines and the size of the 2
   * files.
   */
  public float compareTrees() {
    // Iterates through all of the lines in both HashMaps and compares them.
    for (HashMap.Entry<Integer, Integer> firstEntry : this.firstHashedLines.entrySet()) {
      for (HashMap.Entry<Integer, Integer> secondEntry : this.secondHashedLines.entrySet()) {
        if (Math.abs(firstEntry.getValue() - secondEntry.getValue()) < 10) {
          this.compareLines.put(firstEntry.getKey(), secondEntry.getKey());
        }
      }
    }

    // The math for finding percentage.
    int overall = this.firstHashedLines.size() + this.secondHashedLines.size();
    return ((float) this.compareLines.size() * 2 / overall) * 100;
  }

  /**
   * A getter for the similar lines HashMap.
   *
   * @return the similar lines HashMap.
   */
  public HashMap<Integer, Integer> getCompareLines() {
    return this.compareLines;
  }

}
