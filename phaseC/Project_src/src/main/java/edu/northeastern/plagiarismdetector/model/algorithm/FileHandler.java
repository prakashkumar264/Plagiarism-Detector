package edu.northeastern.plagiarismdetector.model.algorithm;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import edu.northeastern.plagiarismdetector.util.antlr4.CPP14Grammar.CPP14BaseListener;
import edu.northeastern.plagiarismdetector.util.antlr4.CPP14Grammar.CPP14Lexer;
import edu.northeastern.plagiarismdetector.util.antlr4.CPP14Grammar.CPP14Parser;
import edu.northeastern.plagiarismdetector.util.antlr4.Python3Grammar.Python3BaseListener;
import edu.northeastern.plagiarismdetector.util.antlr4.Python3Grammar.Python3Lexer;
import edu.northeastern.plagiarismdetector.util.antlr4.Python3Grammar.Python3Parser;

/**
 * This class uses the Antlr files to read a source code file.
 */
class FileHandler {

  /**w
   * This is the parser for Python files.
   *
   * @param file the file to be parsed.
   * @return the parsed file.
   * @throws Exception if the file cannot be parsed.
   */
  Python3Parser.File_inputContext parsePython(String file) throws Exception {
    CharStream codePointCharStream = CharStreams.fromFileName(file);
    Python3Lexer lexer = new Python3Lexer(codePointCharStream);
    Python3Parser parser = new Python3Parser(new CommonTokenStream(lexer));
    parser.addParseListener(new Python3BaseListener());
    return parser.file_input();
  }

  /**
   * This is the CPP parser.
   *
   * @param file the file to be parsed.
   * @return the parsed file.
   * @throws Exception if the file cannot be parsed.
   */
  CPP14Parser.TranslationunitContext parseCPP(String file) throws Exception {
    CharStream codePointCharStream = CharStreams.fromFileName(file);
    CPP14Lexer lexer = new CPP14Lexer(codePointCharStream);
    CPP14Parser parser = new CPP14Parser(new CommonTokenStream(lexer));
    parser.addParseListener(new CPP14BaseListener());
    return parser.translationunit();
  }

}
