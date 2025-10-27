package com.web.lab7.strategy.delivery;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.web.lab7.model.flower.CactusFlower;
import org.junit.jupiter.api.Test;

class DeliveryStrategyTest {

    @Test
    void postDeliveryIncludesAddress() {
        PostDeliveryStrategy delivery = new PostDeliveryStrategy("Kyiv, Main Street 1");
        String message = delivery.deliver(new CactusFlower());
        assertTrue(message.contains("post"));
        assertTrue(message.contains("Main Street"));
    }

    @Test
    void dhlDeliveryMentionsWarehouse() {
        DHLDeliveryStrategy delivery = new DHLDeliveryStrategy("Warehouse 99");
        String message = delivery.deliver(new CactusFlower());
        assertTrue(message.contains("DHL"));
        assertTrue(message.contains("Warehouse 99"));
    }
}
