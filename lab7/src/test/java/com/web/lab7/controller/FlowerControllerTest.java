package com.web.lab7.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.FlowerService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = FlowerController.class)
class FlowerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SuppressWarnings("removal")
  @MockBean
  private FlowerService flowerService;

  @Test
  void getAllReturnsSeedData() throws Exception {
    Flower sample = new Flower(FlowerType.ROSE, FlowerColor.RED, 20, 40);
    given(flowerService.findAll()).willReturn(List.of(sample));

    mockMvc.perform(get("/api/v1/flower"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].type").value("ROSE"));
  }

  @Test
  void postCreatesFlower() throws Exception {
    Flower saved = new Flower(FlowerType.ROSE, FlowerColor.RED, 15, 25);
    ReflectionTestUtils.setField(saved, "id", 1L);
    given(flowerService.save(any())).willReturn(saved);

    String payload = """
        {
          \"type\": \"ROSE\",
          \"color\": \"RED\",
          \"sepalLength\": 15,
          \"price\": 25
        }
        """;

    mockMvc.perform(post("/api/v1/flower")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.type").value("ROSE"));

    ArgumentCaptor<Flower> captor = ArgumentCaptor.forClass(Flower.class);
    verify(flowerService).save(captor.capture());
    assertThat(captor.getValue().getType()).isEqualTo(FlowerType.ROSE);
    assertThat(captor.getValue().getColor()).isEqualTo(FlowerColor.RED);
  }
}
