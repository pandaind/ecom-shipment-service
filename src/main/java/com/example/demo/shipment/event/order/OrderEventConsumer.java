package com.example.demo.shipment.event.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;

    private final OrderEventProcessor orderEventProcessor;

    @Autowired
    public OrderEventConsumer(ObjectMapper objectMapper, OrderEventProcessor orderEventProcessor) {
        this.objectMapper = objectMapper;
        this.orderEventProcessor = orderEventProcessor;
    }

    @KafkaListener(topics = "order-events")
    public void orderEventConsumer(ConsumerRecord<String, String> orderEvent) throws JsonProcessingException {
        log.info("@@@@@@@@@@@@@@ "+ orderEvent);
        OrderEvent event = this.objectMapper.readValue(orderEvent.value(), OrderEvent.class);

        this.orderEventProcessor.processOrderEvent(event);
    }

}
