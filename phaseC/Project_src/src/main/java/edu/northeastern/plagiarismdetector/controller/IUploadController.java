package edu.northeastern.plagiarismdetector.controller;

import org.springframework.ui.Model;

/**
 * This interface contains all operations that all Upload Controller classes should contain. It
 * should be able to read files and display the results from the model.
 */
public interface IUploadController {

  /**
   * This method takes in an ANTLR node and runs the algorithm on it.
   *
   * @param model the model for the algorithm
   * @return String that holds the similar lines
   */
  String displayResults(Model model) throws Exception;

}
