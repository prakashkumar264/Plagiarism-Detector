package edu.northeastern.plagiarismdetector.service;

/**
 * Interface TempFileStorage contains operations a service manipulating files should contain.
 */
public interface TempFileStorage {

  /**
   * Method deleteAll deletes the temporary filestorage directory and pkg1 and pkg2 subdirectories
   * for upload of files to be analyzed. Method also deleted the files contained in filestorage.
   * This is called in the PlagiarismDetectorApplication in main at spring application runtime.
   *
   * @throws Exception if there is an error deleting a directory or file.
   */
  void deleteAll() throws Exception;

  /**
   * Method init creates the temporary filestorage directory and pkg1 and pkg2 subdirectories for
   * upload of files to be analyzed. Method is called after deleteAll so no leftover files remain.
   * This is called in the PlagiarismDetectorApplication in main at spring application runtime.
   *
   * @throws Exception if there is an error creating a directory.
   */
  void init() throws Exception;
}
