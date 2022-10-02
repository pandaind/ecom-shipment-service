package com.example.demo.shipment.event.order;

import com.example.demo.shipment.service.dto.OrderDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {
    private String eventId;
    private OrderEventType type;
    private OrderDTO order;
}
