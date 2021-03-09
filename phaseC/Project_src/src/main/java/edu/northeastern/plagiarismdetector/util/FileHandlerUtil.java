package edu.northeastern.plagiarismdetector.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class is used to find all files and subdirectories of a parent directory. Lists of
 * File objects for the files and subdirectory structure and their respective counts can be obtained
 * using the appropriate getter methods. This class is capable of handling both an individual file
 * or directory as well as a nested directory structure.
 */
public class FileHandlerUtil {
  private File root;
  private List<File> listOfFiles;
  private List<File> listOfDirectories;

  /**
   * FileHandler constructor method. The root file is null when initialized.
   */
  public FileHandlerUtil() {
    this.root = null;
    this.listOfDirectories = new ArrayList<>();
    this.listOfFiles = new ArrayList<>();
  }

  /**
   * setInDirectory method assigns a provided filepath as a new root file object.
   *
   * @param pathString represents a filepath for a directory or individual file.
   * @throws Exception if the provided path is invalid.
   */
  private void setInDirectory(String pathString) throws Exception {
    this.root = new File(pathString);

    if (!this.root.exists()) {
      throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
    }
  }


  /**
   * getFileList method returns a list of file objects found, not including directory file objects.
   *
   * @return ArrayList</ File> of non-directory file objects found.
   */
  public List<File> getFileList() {
    return this.listOfFiles;
  }

  /**
   * getDirectoryList method returns a list of directory file objects found.
   *
   * @return ArrayList</ File> of directory file objects found.
   */
  public List<File> getDirectoryList() {
    return this.listOfDirectories;
  }

  /**
   * getNumFiles method returns the count of file objects found, not including directory file
   * objects.
   *
   * @return int representing the number of non-directory file objects found.
   */
  public int getNumFiles() {
    return this.listOfFiles.size();
  }

  /**
   * getNumDirectories method returns the count of directory file objects found.
   *
   * @return int representing the number of directory file objects found.
   */
  public int getNumDirectories() {
    return this.listOfDirectories.size();
  }

  /**
   * fileWalk method explores the file structure of a provided string file path. If the provided
   * path is invalid an exception is thrown. Each new file object and directory file object found
   * are added to their respective ArrayLists.
   *
   * @param path represents a filepath for a directory or individual file.
   * @throws Exception if the provided path is invalid.
   */
  public void fileWalk(String path) throws Exception {

    if (path == null) {
      throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
    }

    if (this.root == null) {
      try {
        this.setInDirectory(path);
      } catch (Exception e) {
        throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
      }
    }

    File dir = new File(path);
    File[] arrFiles = dir.listFiles();

    if (arrFiles != null) {
      for (File file : arrFiles) {
        if (file.isFile() && !file.isDirectory()) {
          this.listOfFiles.add(file);
        } else if (file.isDirectory()) {
          this.listOfDirectories.add(file);
          this.fileWalk(file.getAbsolutePath());
        }
      }
    }
  }
}
