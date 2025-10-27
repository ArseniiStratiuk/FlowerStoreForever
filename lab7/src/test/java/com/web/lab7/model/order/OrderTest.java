package com.web.lab7.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.FlowerService;
import com.web.lab7.strategy.delivery.PostDeliveryStrategy;
import com.web.lab7.strategy.payment.PayPalPaymentStrategy;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void processOrderChargesPaymentAndProducesDeliveryMessages() {
        FlowerService flowerService = new FlowerService();
        Flower flower = flowerService.createFlower(FlowerType.ROSE);

        Order order = new Order();
        order.addItem(flower);

        // Set simple strategies that will succeed
        order.setPaymentStrategy(new PayPalPaymentStrategy("buyer@example.com"));
        order.setDeliveryStrategy(new PostDeliveryStrategy("Somewhere 1"));

        Order.OrderProcessingResult result = order.processOrder();

        assertEquals(flower.price(), result.totalPrice(), 0.0001);
        assertTrue(result.paid());
        List<String> messages = result.deliveryMessages();
        assertEquals(1, messages.size());
        assertTrue(messages.get(0).contains("post") || messages.get(0).toLowerCase().contains("dhl"));
    }
}
