package com.example.demo.shipment.event.shipment;

import com.example.demo.shipment.service.dto.ShipmentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentEvent {
    private String eventId;
    private ShipmentEvent type;
    private ShipmentDTO shipment;
}
