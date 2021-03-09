package edu.northeastern.plagiarismdetector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.northeastern.plagiarismdetector.controller.UploadFilesController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * PlagiarismDetectorApplication test class.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlagiarismDetectorApplicationTests {

  private MockMvc mvc;

  @InjectMocks
  private UploadFilesController controller;

  @Before
  public void setup() {
    mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void contentLoads() throws Exception {
    MockHttpServletResponse response = mvc.perform(get("/"))
            .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

}
