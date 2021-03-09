package edu.northeastern.plagiarismdetector.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import edu.northeastern.plagiarismdetector.service.FileService;
import edu.northeastern.plagiarismdetector.util.ExceptionMessage;
import edu.northeastern.plagiarismdetector.util.FileHandlerUtil;
import edu.northeastern.plagiarismdetector.util.SupportedLanguages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Test class for UploadFilesController.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadFilesControllerTest {

  private static Properties p;
  private static String testUploadDir;
  private static String testPkg1Dir;
  private static String testPkg2Dir;
  private static String cppPkgDir;
  private static String pythonPkgDir;
  private static String cppHelloworld;
  private static String hOpenBSDstdlib;
  private static String pythonHelloworld;
  private static String javaHelloworld;

  private MockMvc mockMvc;
  private MockMvc mvc;

  @Mock
  private FileService fileService;

  @InjectMocks
  private UploadFilesController controller;

  @BeforeClass
  public static void before() {
    p = new Properties();

    try {
      p.load(new FileReader(new File("./src/test/resources/application.properties")));
    } catch (IOException e) {
      e.printStackTrace();
    }

    testUploadDir = p.getProperty("upload.path");
    testPkg1Dir = p.getProperty("pkg1.path");
    testPkg2Dir = p.getProperty("pkg2.path");

    pythonPkgDir = p.getProperty("python_pkg.path");
    pythonHelloworld = p.getProperty("python.helloworld");

    cppPkgDir = p.getProperty("cpp_pkg.path");
    cppHelloworld = p.getProperty("cpp.helloworld");
    hOpenBSDstdlib = p.getProperty("h.openbsd_stdlib");

    javaHelloworld = p.getProperty("java.helloworld");
  }

  @Before
  public void setup() {
    mvc = MockMvcBuilders.standaloneSetup(controller).build();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();
  }

  @Test
  public void contentLoads() throws Exception {
    MockHttpServletResponse response = mvc.perform(get("/"))
            .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void testUploadFiles() {
    UploadFilesController controller = new UploadFilesController();
    String result = controller.uploadFiles();

    assertEquals(result, "uploadFiles");
  }

  @Test
  public void testCreateListsPythonSingle() throws Exception {

    String language = SupportedLanguages.Python.getLang();

    File f1 = new File(pythonHelloworld);
    File f2 = new File(pythonHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };


    try {
      String result = controller.createLists(pkg1, pkg2, language);
      assertEquals(result, "redirect:/displayResults");
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonSingle1() throws Exception {

    String language = SupportedLanguages.Python.getLang();

    File f1 = new File(pythonHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    String result = controller.createLists(pkg1, null, language);

  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonSingle2() throws Exception {

    String language = SupportedLanguages.Python.getLang();

    File f2 = new File(pythonHelloworld);

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };


    String result = controller.createLists(null, pkg2, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonSingle3() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f1 = new File(pythonHelloworld);
    File f2 = new File(pythonHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };

    String result = controller.createLists(pkg1, pkg2, language);

  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonSingle4() throws Exception {

    File f1 = new File(pythonHelloworld);
    File f2 = new File(pythonHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };


    String result = controller.createLists(pkg1, pkg2, "Java");
  }

  @Test
  public void testCreateListsCPPSingle1() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f1 = new File(cppHelloworld);
    File f2 = new File(cppHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };


    try {
      String result = controller.createLists(pkg1, pkg2, language);
      assertEquals(result, "redirect:/displayResults");
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateListsCPPSingle2() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f1 = new File(hOpenBSDstdlib);
    File f2 = new File(cppHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };


    try {
      String result = controller.createLists(pkg1, pkg2, language);
      assertEquals(result, "redirect:/displayResults");
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPSingle1() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f1 = new File(cppHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    String result = controller.createLists(pkg1, null, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPSingle2() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f2 = new File(cppHelloworld);

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };

    String result = controller.createLists(null, pkg2, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPSingle3() throws Exception {

    String language = SupportedLanguages.CPP.getLang();

    File f1 = new File(cppHelloworld);
    File f2 = new File(pythonHelloworld);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };

    String result = controller.createLists(pkg1, pkg2, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPSingle4() throws Exception {

    File f1 = new File(cppHelloworld);
    File f2 = new File(hOpenBSDstdlib);

    MultipartFile[] pkg1 = {
            new MockMultipartFile(
                    f1.getName(),
                    f1.getPath(),
                    Files.probeContentType(f1.toPath()),
                    Files.readAllBytes(f1.toPath()))
    };

    MultipartFile[] pkg2 = {
            new MockMultipartFile(
                    f2.getName(),
                    f2.getPath(),
                    Files.probeContentType(f2.toPath()),
                    Files.readAllBytes(f2.toPath()))
    };

    String result = controller.createLists(pkg1, pkg2, "Java");
  }

  @Test
  public void testCreateListsPythonMulti() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    FileHandlerUtil fileHandlerUtil2 = new FileHandlerUtil();
    String language = SupportedLanguages.Python.getLang();

    try {
      fileHandlerUtil1.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }

    try {
      fileHandlerUtil2.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg2 = new MultipartFile[fileHandlerUtil2.getNumFiles()];
    int j = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg2[j] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      j++;
    }


    try {
      String result = controller.createLists(pkg1, pkg2, language);
      assertEquals(result, "redirect:/displayResults");
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonMulti1() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    FileHandlerUtil fileHandlerUtil2 = new FileHandlerUtil();
    String language = SupportedLanguages.Python.getLang();

    try {
      fileHandlerUtil1.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }

    try {
      fileHandlerUtil2.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg2 = new MultipartFile[fileHandlerUtil2.getNumFiles()];
    int j = 0;
    for (File f : fileHandlerUtil2.getFileList()) {
      pkg2[j] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      j++;
    }

    String result = controller.createLists(pkg1, pkg2, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsPythonMulti2() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    String language = SupportedLanguages.Python.getLang();

    try {
      fileHandlerUtil1.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }

    String result = controller.createLists(pkg1, null, language);
  }

  @Test
  public void testCreateListsCPPMulti() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    FileHandlerUtil fileHandlerUtil2 = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil1.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }

    try {
      fileHandlerUtil2.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg2 = new MultipartFile[fileHandlerUtil2.getNumFiles()];
    int j = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg2[j] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      j++;
    }

    try {
      String result = controller.createLists(pkg1, pkg2, language);
      assertEquals(result, "redirect:/displayResults");
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPMulti1() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    FileHandlerUtil fileHandlerUtil2 = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil1.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }

    try {
      fileHandlerUtil2.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg2 = new MultipartFile[fileHandlerUtil2.getNumFiles()];
    int j = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg2[j] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      j++;
    }

    String result = controller.createLists(pkg1, pkg2, language);
  }

  @Test(expected = Exception.class)
  public void testFailCreateListsCPPMulti2() throws Exception {

    FileHandlerUtil fileHandlerUtil1 = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil1.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }
    MultipartFile[] pkg1 = new MultipartFile[fileHandlerUtil1.getNumFiles()];
    int i = 0;
    for (File f : fileHandlerUtil1.getFileList()) {
      pkg1[i] = new MockMultipartFile(
              f.getName(),
              f.getPath(),
              Files.probeContentType(f.toPath()),
              Files.readAllBytes(f.toPath()));
      i++;
    }
    String result = controller.createLists(pkg1, null, language);
  }

  @Test
  public void testSuccessPythonLanguageCheck() {
    UploadFilesController controller = new UploadFilesController();
    FileHandlerUtil fileHandlerUtil = new FileHandlerUtil();
    String language = SupportedLanguages.Python.getLang();

    try {
      fileHandlerUtil.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }

    for (File f : fileHandlerUtil.getFileList()) {
      try {
        controller.languageCheck(language, f.getAbsolutePath());
      } catch (Exception e) {
        fail("An exception should not have been thrown");
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testFailPythonLanguageCheck() {
    UploadFilesController controller = new UploadFilesController();
    FileHandlerUtil fileHandlerUtil = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }

    for (File f : fileHandlerUtil.getFileList()) {
      try {
        controller.languageCheck(language, f.getAbsolutePath());
        fail("An exception should have been thrown");
      } catch (Exception e) {
        Assert.assertEquals(ExceptionMessage.UNSUPPORTED_FILE_EXT.getMsg(), e.getMessage());
      }
    }
  }

  @Test
  public void testSuccessCPPLanguageCheck() {
    UploadFilesController controller = new UploadFilesController();
    FileHandlerUtil fileHandlerUtil = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil.fileWalk(cppPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }

    for (File f : fileHandlerUtil.getFileList()) {
      try {
        controller.languageCheck(language, f.getAbsolutePath());
      } catch (Exception e) {
        fail("An exception should not have been thrown");
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testFailCPPLanguageCheck() {
    UploadFilesController controller = new UploadFilesController();
    FileHandlerUtil fileHandlerUtil = new FileHandlerUtil();
    String language = SupportedLanguages.CPP.getLang();

    try {
      fileHandlerUtil.fileWalk(pythonPkgDir);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
      e.printStackTrace();
    }

    for (File f : fileHandlerUtil.getFileList()) {
      try {
        controller.languageCheck(language, f.getAbsolutePath());
        fail("An exception should have been thrown");
      } catch (Exception e) {
        Assert.assertEquals(ExceptionMessage.UNSUPPORTED_FILE_EXT.getMsg(), e.getMessage());
      }
    }
  }

  @Test
  public void testFailUnsupportedLanguageCheck1() {
    UploadFilesController controller = new UploadFilesController();
    String language = "Java";

    try {
      controller.languageCheck(language, javaHelloworld);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.UNSUPPORTED_LANG.getMsg(), e.getMessage());
    }
  }

  @Test
  public void testFailUnsupportedLanguageCheck2() {
    UploadFilesController controller = new UploadFilesController();
    String language = "";

    try {
      controller.languageCheck(language, javaHelloworld);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.UNSUPPORTED_LANG.getMsg(), e.getMessage());
    }
  }

  @Test
  public void testFailUnsupportedLanguageCheck3() {
    UploadFilesController controller = new UploadFilesController();
    String language = null;

    try {
      controller.languageCheck(language, javaHelloworld);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.UNSUPPORTED_LANG.getMsg(), e.getMessage());
    }
  }

  @Test
  public void testFailInvalidFilePathLanguageCheck1() {
    UploadFilesController controller = new UploadFilesController();
    String language = SupportedLanguages.CPP.getLang();

    try {
      controller.languageCheck(language, "");
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.INVALID_FILE_PATH.getMsg(), e.getMessage());
    }
  }

  @Test
  public void testFailInvalidFilePathLanguageCheck2() {
    UploadFilesController controller = new UploadFilesController();
    String language = SupportedLanguages.CPP.getLang();

    try {
      controller.languageCheck(language, null);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.INVALID_FILE_PATH.getMsg(), e.getMessage());
    }
  }

  @Test
  public void testFailInvalidFilePathLanguageCheck3() {
    UploadFilesController controller = new UploadFilesController();
    String language = SupportedLanguages.CPP.getLang();

    try {
      controller.languageCheck(language, pythonPkgDir);
      fail("An exception should have been thrown");
    } catch (Exception e) {
      Assert.assertEquals(ExceptionMessage.INVALID_FILE_PATH.getMsg(), e.getMessage());
    }
  }

}