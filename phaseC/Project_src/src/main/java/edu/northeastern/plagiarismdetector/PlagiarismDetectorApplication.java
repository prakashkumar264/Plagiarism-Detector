package edu.northeastern.plagiarismdetector;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import edu.northeastern.plagiarismdetector.service.TempFileStorage;

/**
 * PlagiarismDetectorApplication is main driver. At runtime, directories for temp storage of
 * uploaded files are deleted and recreated.
 */
@SpringBootApplication
public class PlagiarismDetectorApplication implements CommandLineRunner {

  @Resource
  TempFileStorage tempFileStorage;

  public static void main(String[] args) {
    SpringApplication.run(PlagiarismDetectorApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    tempFileStorage.deleteAll();
    tempFileStorage.init();
  }
}