package com.web.lab7.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.DeliveryService;
import com.web.lab7.service.FlowerService;
import com.web.lab7.strategy.delivery.Delivery;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private DeliveryService deliveryService;

    @SuppressWarnings("removal")
    @MockBean
    private FlowerService flowerService;

    @Test
    void getAllReturnsStrategyNames() throws Exception {
        given(deliveryService.getNames()).willReturn(List.of("post", "dhl"));

        mockMvc.perform(get("/api/v1/delivery"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("post"));
    }

    @Test
    void simulateReturnsMessage() throws Exception {
        Flower flower = new Flower(FlowerType.ROSE, FlowerColor.RED, 20, 40);
        given(flowerService.requireByType(FlowerType.ROSE)).willReturn(flower);

        Delivery delivery = mock(Delivery.class);
        given(delivery.deliver(flower)).willReturn("Delivered rose");
        given(deliveryService.resolve(eq("post"))).willReturn(delivery);

        mockMvc.perform(get("/api/v1/delivery/simulate")
                        .param("method", "post")
                        .param("itemType", "ROSE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.method").value("post"))
                .andExpect(jsonPath("$.message").value("Delivered rose"))
                .andExpect(jsonPath("$.itemDescription").value("ROSE flower"));
    }
}
