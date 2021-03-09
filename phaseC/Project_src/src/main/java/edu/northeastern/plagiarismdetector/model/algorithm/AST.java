package edu.northeastern.plagiarismdetector.model.algorithm;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashMap;

import edu.northeastern.plagiarismdetector.util.ExceptionMessage;
import edu.northeastern.plagiarismdetector.util.SupportedLanguages;

/**
 * This is the class creates the AST for the file and runs the algorithm on it.
 */
public class AST {

  // The hashed line values for the file.
  private HashMap hashedPath;
  // The ParserRuleContext used for the algorithm.
  private ParserRuleContext parsedFile;

  /**
   * Passes through a path, creates an AST and then runs the algorithm.
   *
   * @param path     the file to be hashed.
   * @param language the language to be parsed.
   * @throws Exception if the file input is unable to be parsed.
   */
  public AST(String path, String language) throws Exception {
    // This uses the Antlr generated files to create the parsed file
    // based on the source code language.
    FileHandler algoImpl = new FileHandler();

    if (language.equals(SupportedLanguages.Python.getLang())) {
      try {
        this.parsedFile = algoImpl.parsePython(path);
      } catch (Exception e) {
        throw new Exception(ExceptionMessage.ERR_ANTLR_FILE_PARSE.getMsg());
      }
    } else if (language.equals(SupportedLanguages.CPP.getLang())) {
      try {
        this.parsedFile = algoImpl.parseCPP(path);
      } catch (Exception e) {
        throw new Exception(ExceptionMessage.ERR_ANTLR_FILE_PARSE.getMsg());
      }
    }

    // Runs the algorithm.
    Algorithm ast = new Algorithm();
    ast.calculateHash(parsedFile, language);
    this.hashedPath = ast.getHashMap();
  }

  /**
   * A getter for the hashed line values.
   *
   * @return the hasehd line values.
   */
  public HashMap<Integer, Integer> getHashedPath() {
    return this.hashedPath;
  }

}
