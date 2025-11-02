package com.web.lab7.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.web.lab7.service.PaymentService;
import com.web.lab7.strategy.payment.Payment;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private PaymentService paymentService;

    @Test
    void getAllReturnsStrategyNames() throws Exception {
        given(paymentService.getNames()).willReturn(List.of("credit-card", "paypal"));

        mockMvc.perform(get("/api/v1/payment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("credit-card"));
    }

    @Test
    void simulateReturnsResult() throws Exception {
    Payment payment = mock(Payment.class);
    given(payment.getName()).willReturn("credit-card");
    given(payment.pay(100)).willReturn(true);
    given(paymentService.resolve(eq("credit-card"))).willReturn(payment);

        mockMvc.perform(get("/api/v1/payment/simulate")
                        .param("method", "credit-card")
                        .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.method").value("credit-card"))
                .andExpect(jsonPath("$.success").value(true));
    }
}
