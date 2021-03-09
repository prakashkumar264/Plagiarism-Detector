package edu.northeastern.plagiarismdetector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import edu.northeastern.plagiarismdetector.model.algorithm.AST;
import edu.northeastern.plagiarismdetector.util.ExceptionMessage;

/**
 * This is the service that hashes the lines of the files passed in.
 */
@Service
public class FileService implements TempFileStorage {

  // This is the upload directory for the temp files, used for reference in the UploadFilesController.
  @Value("${upload.path}")
  public String rootUploadDir;

  // Destination storage location for temp files in package 1.
  @Value("${pkg1.path}")
  public String pkg1Dir;

  // Destination storage location for temp files in package 1.
  @Value("${pkg2.path}")
  public String pkg2Dir;

  // These are both used to check the package that is passed by the controller.
  @Value("${pkg1}")
  public String pkg1;
  @Value("${pkg2}")
  public String pkg2;

  // The result of the uploadFile method of the hashed line values and the file's path.
  private HashMap<HashMap<Integer, Integer>, Path> hashedWithPath;

  /**
   * This is the main method in the service that runs the hashing algorithm on the file.
   *
   * @param file the file being passed in.
   * @param path the package that the file is associated with.
   * @return hashedWithPath, which is a HashMap of the hashed line values and the file's path.
   * @throws Exception if the copying of files doesn't work.
   */
  public HashMap<HashMap<Integer, Integer>, Path> uploadFile(MultipartFile file,
                                                             String path,
                                                             String language) throws Exception {

    try {
      Path copyLocation;
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());

      // Gets the location to store the file and stores it.
      if (path.equals(pkg1)) {
        copyLocation = Paths.get(pkg1Dir + File.separator + fileName);
      } else if (path.equals(pkg2)) {
        copyLocation = Paths.get(pkg2Dir + File.separator + fileName);
      } else {
        throw new Exception(ExceptionMessage.INVALID_FILE_PATH.getMsg());
      }
      Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

      // Runs the algorithm and returns the HashMap.
      hashedWithPath = new HashMap<>();
      AST ast = new AST(copyLocation.toString(), language);
      hashedWithPath.put(ast.getHashedPath(), copyLocation);
      return hashedWithPath;

    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(ExceptionMessage.FILE_ERROR.getMsg() +
              file.getOriginalFilename() +
              ExceptionMessage.COULD_NOT_STORE_FILE.getMsg());
    }
  }

  @Override
  public void deleteAll() throws Exception {
    rmFile(pkg1Dir, ExceptionMessage.ERR_DEL_FILE.getMsg());
    rmFile(pkg2Dir, ExceptionMessage.ERR_DEL_FILE.getMsg());
    rmFile(rootUploadDir, ExceptionMessage.ERR_DEL_ROOT_DIR.getMsg());
    rmFile(pkg1, ExceptionMessage.ERR_DEL_PKG1_DIR.getMsg());
    rmFile(pkg1, ExceptionMessage.ERR_DEL_PKG2_DIR.getMsg());
  }

  @Override
  public void init() throws Exception {
    mkDir(rootUploadDir, ExceptionMessage.ERR_INIT_ROOT_DIR.getMsg());
    mkDir(pkg1Dir, ExceptionMessage.ERR_INIT_PKG1_DIR.getMsg());
    mkDir(pkg2Dir, ExceptionMessage.ERR_INIT_PKG2_DIR.getMsg());
  }

  private void rmFile(String filePath, String message) throws Exception {
    try {
      FileSystemUtils.deleteRecursively(Paths.get(filePath).toFile());
    } catch (Exception e) {
      throw new Exception(message);
    }
  }

  private void mkDir(String dirPath, String message) throws Exception {
    try {
      Files.createDirectory(Paths.get(dirPath));
    } catch (Exception e) {
      throw new Exception(message);
    }
  }
}