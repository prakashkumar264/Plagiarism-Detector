package edu.northeastern.plagiarismdetector.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import edu.northeastern.plagiarismdetector.model.algorithm.CompareList;

/**
 * This service compares 2 files based on the hashed line values created in the FileService class.
 */
@Service
public class CompareService {

  // The Comparing object that is needed to store the line values and percentages.
  private CompareList comparator;
  // The return result of the compareMaps method, which stores the percentage of detected plagiarism
  // and the HashMap of similar lines.
  private HashMap<Float, HashMap<Integer, Integer>> percWithLines;

  /**
   * This is the method that compares the 2 files.
   *
   * @param firstProject  the first file.
   * @param secondProject the second file.
   * @return the HashMap of percent plagiarism and similar lines.
   */
  public HashMap<Float, HashMap<Integer, Integer>> compareMaps(HashMap<Integer, Integer> firstProject,
                                                               HashMap<Integer, Integer> secondProject) {

    percWithLines = new HashMap<>();
    comparator = new CompareList(firstProject, secondProject);

    percWithLines.put(comparator.compareTrees(), comparator.getCompareLines());

    return percWithLines;
  }
}
