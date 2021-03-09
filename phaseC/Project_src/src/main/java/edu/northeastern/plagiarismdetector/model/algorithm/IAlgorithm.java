package edu.northeastern.plagiarismdetector.model.algorithm;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This interface contains all operations that all Algorithm classes should contain. It should
 * contain a method explore to go through the AST.
 */
public interface IAlgorithm {

  /**
   * This method takes in an ANTLR node and runs the algorithm on it.
   *
   * @param ctx      the head of the ANTLR tree.
   * @param language the source code language.
   */
  void calculateHash(ParserRuleContext ctx, String language);

}
