package edu.northeastern.plagiarismdetector.service;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import edu.northeastern.plagiarismdetector.util.ExceptionMessage;

/**
 * This service takes the paths and the similar lines HashMap created by the CompareService class
 * and gets only the lines needed for the View.
 */
@Service
public class GetLinesService {

  // The List is flooded with HashMaps of the line number and the String text of that line.
  private HashMap<Integer, String> lineAndText;
  private List<HashMap<Integer, String>> fullMaps;

  /**
   * This is the method that actually gets the lines.
   *
   * @param file         the path to the necessary file.
   * @param similarLines the HashMap of similar lines, or plagiarised lines.
   * @param folder       the package that the file was submitted in.
   * @return the List of HashMaps, which are the line numbers and String texts.
   * @throws Exception if lines cannot be retrieved.
   */
  public List<HashMap<Integer, String>> usefulLine(Path file,
                                                   HashMap<Integer, Integer> similarLines,
                                                   int folder) throws Exception {

    lineAndText = new HashMap<>();
    fullMaps = new ArrayList<>();
    String specific_line_n = "";

    // This goes through the HashMap for line numbers.
    for (HashMap.Entry<Integer, Integer> entry : similarLines.entrySet()) {
      lineAndText = new HashMap<>();
      // This is the best way to get to specific lines for larger files.
      try (Stream<String> all_lines = Files.lines(Paths.get(file.toString()))) {
        if (folder == 1) {
          specific_line_n = all_lines.skip(entry.getKey() - 1).findFirst().get();
          lineAndText.put(entry.getKey(), specific_line_n);
          fullMaps.add(lineAndText);
        } else {
          specific_line_n = all_lines.skip(entry.getValue() - 1).findFirst().get();
          lineAndText.put(entry.getValue(), specific_line_n);
          fullMaps.add(lineAndText);
        }
      } catch (Exception e) {
        throw new Exception(ExceptionMessage.ERR_GET_LINES.getMsg());
      }
    }
    return fullMaps;
  }
}
