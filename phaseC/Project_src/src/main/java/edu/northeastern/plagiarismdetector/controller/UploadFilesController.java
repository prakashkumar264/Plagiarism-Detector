package edu.northeastern.plagiarismdetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.northeastern.plagiarismdetector.service.CompareService;
import edu.northeastern.plagiarismdetector.service.FileService;
import edu.northeastern.plagiarismdetector.service.GetLinesService;
import edu.northeastern.plagiarismdetector.util.ExceptionMessage;
import edu.northeastern.plagiarismdetector.util.FileExt;
import edu.northeastern.plagiarismdetector.util.SupportedLanguages;

@Controller
public class UploadFilesController implements IUploadController {

  /**
   * The FileService object that will be used to access the algorithm models.
   */
  @Autowired
  private FileService fileService;

  /**
   * The CompareService object that will be used to compare two files.
   */
  @Autowired
  private CompareService compareService;

  /**
   * The GetLinesService object that will be used to get the String lines for the view.
   */
  @Autowired
  private GetLinesService getLinesService;

  /**
   * These are the Lists of HashMaps that our algorithm gives us. They contain the line number as
   * the keys and the hashed value that our algorithm gives us as the value.
   */
  private List<HashMap<Integer, Integer>> folderOne;
  private List<HashMap<Integer, Integer>> folderTwo;

  /**
   * These are the lists that the view gets. They are the Lists of String blocks that contain the
   * similarities between files.
   */
  private List<String> sameLinesFirst;
  private List<String> sameLinesSecond;

  /**
   * The Lists of the paths for both projects. We use these to reference specific lines of text.
   */
  private List<Path> firstPaths;
  private List<Path> secondPaths;

  /**
   * These 3 Lists are given to us by the CompareService. The percentage List is the list of
   * percentage plagiarism detected. The similarLineNumbers List is the list of HashMaps that
   * contain the line number in project 1 as the key and the line number in project 2 as the value.
   * The fileIndex List uses the Pair class to let us keep track of our comparison relationships
   * between files by indexing them.
   */
  private List<Integer> percentages;
  private List<HashMap<Integer, Integer>> similarLineNumbers;
  private List<Pair<Integer, Integer>> fileIndex;

  /**
   * pkg1 is the location in the folder filestorage where we store the first project.
   */
  @Value("${pkg1}")
  private String pkg1;

  /**
   * pkg2 is the location in the folder filestorage where we store the second project.
   */
  @Value("${pkg2}")
  private String pkg2;

  /**
   * This method initializes the view for uploading files.
   *
   * @return the uploadFiles.html file in resources, which displays the upload files page.
   */
  @GetMapping("/")
  public String uploadFiles() {
    return "uploadFiles";
  }

  /**
   * This is the PostMapping when the user uploads files and submits. It takes those files, uses the
   * FileService and then redirects to the displayResults mapping to take care of the algorithm and
   * results view.
   *
   * @param firstProject  the files in the first project.
   * @param secondProject the files in the second project.
   * @param language      the language (C++, Python) selected by the user.
   * @return a mapping to displayResults.
   * @throws Exception if the user provided file is invalid, of unsupported extension, or an
   *                   unsupported language is selected.
   */
  @PostMapping("/uploadFolder")
  public String createLists(@RequestParam("firstFiles") MultipartFile[] firstProject,
                            @RequestParam("secondFiles") MultipartFile[] secondProject,
                            @RequestParam("language") String language) throws Exception {

    folderOne = new ArrayList<>();
    folderTwo = new ArrayList<>();
    firstPaths = new ArrayList<>();
    secondPaths = new ArrayList<>();

    // This performs the hashing of lines for every file.
    try {
      readFiles(firstProject, folderOne, firstPaths, pkg1, language);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    try {
      readFiles(secondProject, folderTwo, secondPaths, pkg2, language);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    return "redirect:/displayResults";
  }

  /**
   * This is where the bulk of the project takes place. The comparisons are executed here and the
   * view displays our results.
   *
   * @param model the Model object that we use to pass information to the View.
   * @return a mapping to the displayResults.html with the data that we've accumulated.
   */
  @GetMapping("/displayResults")
  public String displayResults(Model model) throws Exception {

    similarLineNumbers = new ArrayList<>();
    fileIndex = new ArrayList<>();
    percentages = new ArrayList<>();
    sameLinesFirst = new ArrayList<>();
    sameLinesSecond = new ArrayList<>();

    // In these loops, we compare all of the files in Project 1 with all of the files in Project 2.
    for (int i = 0; i < folderOne.size(); i++) {
      for (int j = 0; j < folderTwo.size(); j++) {
        HashMap<Float, HashMap<Integer, Integer>> comparisons =
                compareService.compareMaps(folderOne.get(i), folderTwo.get(j));

        // This separates the information that our compareService returns to us.
        for (HashMap.Entry<Float, HashMap<Integer, Integer>> entry : comparisons.entrySet()) {
          this.percentages.add(Math.round(entry.getKey()));
          this.similarLineNumbers.add(entry.getValue());
          Pair<Integer, Integer> files = new Pair<>(i, j);
          this.fileIndex.add(files);
        }
      }
    }


    // Here is where we get the String value for the lines that are similar between files.
    for (int i = 0; i < percentages.size(); i++) {
      if (percentages.get(i) > 0) {
        List<HashMap<Integer, String>> withStringOne = getLinesService
                .usefulLine(firstPaths.get(fileIndex.get(i).getFirst()),
                        similarLineNumbers.get(i), 1);
        List<HashMap<Integer, String>> withStringTwo = getLinesService
                .usefulLine(secondPaths.get(fileIndex.get(i).getSecond()),
                        similarLineNumbers.get(i), 2);

        // For our view design, we have decided to place the file's name at the top of each
        // text block to distinguish between comparisons.
        String similarBlockOne = firstPaths.get(fileIndex.get(i).getFirst()).toString() + "\n\n";
        String similarBlockTwo = secondPaths.get(fileIndex.get(i).getSecond()).toString() + "\n\n";

        // In this section, we create the text blocks that we display in the view of the
        // line number followed by the text on that line.
        for (HashMap<Integer, String> mapOne : withStringOne) {
          for (HashMap.Entry<Integer, String> entryOne : mapOne.entrySet()) {
            similarBlockOne += entryOne.getKey() + ") " + entryOne.getValue() + "\n";
          }
        }
        for (HashMap<Integer, String> mapTwo : withStringTwo) {
          for (HashMap.Entry<Integer, String> entryTwo : mapTwo.entrySet()) {
            similarBlockTwo += entryTwo.getKey() + ") " + entryTwo.getValue() + "\n";
          }

        }
        // We add all of the text blocks to the Lists of Strings, which the view uses.
        sameLinesFirst.add(similarBlockOne);
        sameLinesSecond.add(similarBlockTwo);
      }
    }

    // This passes the necessary information to the View through the Model object.
    model.addAttribute("percentages", percentages);
    model.addAttribute("packageA", sameLinesFirst);
    model.addAttribute("packageB", sameLinesSecond);

    return "displayResults";
  }

  /**
   * This method was abstracted from the createLists method to perform the actual hashing of file
   * lines.
   *
   * @param project  the project files passed in by the user.
   * @param folder   the folder of hashed line values, which the service adds to upon completion.
   * @param paths    the path List that the service adds to upon completion.
   * @param path     the initial path.
   * @param language the language selected by the user.
   * @throws Exception if the user provided file is invalid, of unsupported extension, or an
   *                   unsupported language is selected.
   */
  private void readFiles(MultipartFile[] project, List<HashMap<Integer, Integer>> folder,
                         List<Path> paths, String path, String language) throws Exception {

    if (project == null) {
      throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
    }
    try {
      for (MultipartFile file : project) {
        try {
          languageCheck(language, file.getOriginalFilename());

          HashMap<HashMap<Integer, Integer>, Path> success;
          success = fileService.uploadFile(file, path, language);

          // Adding the results of service to the respective Lists.
          for (HashMap.Entry<HashMap<Integer, Integer>, Path> entry : success.entrySet()) {
            folder.add(entry.getKey());
            paths.add(entry.getValue());
          }
        } catch (Exception e) {
          throw new Exception(e.getMessage());
        }
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }


  /**
   * This is the file checking method.
   *
   * @param file the file being checked for proper path.
   * @throws Exception if the user passes in an invalid file.
   */
  private void fileCheck(String file) throws Exception {
    if (file == null) {
      throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
    }

    try {
      File f = new File(file);
      if (f.toString() == null || f.isDirectory() || f.toString().equals("")) {
        throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
      }
    } catch (Exception e) {
      throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
    }
  }

  /**
   * This is the language checking method.
   *
   * @param language the language passed in by the user.
   * @param file     the file being checked for proper extension.
   * @throws Exception if the user passes in an unsupported file extension or language.
   */
  void languageCheck(String language, String file) throws Exception {

    fileCheck(file);
    if (language == null) {
      throw new Exception(ExceptionMessage.UNSUPPORTED_LANG.getMsg());
    }

    // Checks Python validity.
    if (language.equals(SupportedLanguages.Python.getLang())) {
      if (!(file.substring(file.length() - 3).equals(FileExt.PY.getExt()))) {
        throw new Exception(ExceptionMessage.UNSUPPORTED_FILE_EXT.getMsg());
      }
      // Checks C++ validity.
    } else if (language.equals(SupportedLanguages.CPP.getLang())) {
      if (!(file.substring(file.length() - 2).equals(FileExt.H.getExt())) &&
              !(file.substring(file.length() - 4).equals(FileExt.CPP.getExt()))) {
        throw new Exception(ExceptionMessage.UNSUPPORTED_FILE_EXT.getMsg());
      }
    } else {
      // This is currently impossible to get to, but might be in a different UI implementation.
      throw new Exception(ExceptionMessage.UNSUPPORTED_LANG.getMsg());
    }
  }

  /***
   * This is a pair class that we use to index the files for reference.
   *
   * @param <S> The first element of the pair.
   * @param <I> The second element of the pair.
   */
  private class Pair<S, I> {
    private S first;
    private I second;

    /**
     * This constructor allows a pair to be created without initialization of first and second. The
     * setters would have to be used in this instance.
     */
    protected Pair() {
      this.first = null;
      this.second = null;
    }

    /**
     * This constructor creates the pair when the elements are known ahead of time.
     *
     * @param f the first element.
     * @param s the second element.
     */
    protected Pair(S f, I s) {
      this.first = f;
      this.second = s;
    }

    /**
     * Sets the first element of the pair.
     *
     * @param f the first element of the pair.
     */
    private void setFirst(S f) {
      this.first = f;
    }

    /**
     * Sets the second element of the pair.
     *
     * @param s the second element of the pair.
     */
    private void setSecond(I s) {
      this.second = s;
    }

    /**
     * Gets the first element of the pair.
     *
     * @return the first element of the pair.
     */
    protected S getFirst() {
      return this.first;
    }

    /**
     * Gets the second element of the pair.
     *
     * @return the second element of the pair.
     */
    protected I getSecond() {
      return this.second;
    }
  }
}