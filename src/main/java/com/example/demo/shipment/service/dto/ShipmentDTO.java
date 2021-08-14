package com.example.demo.shipment.service.dto;

import com.example.demo.shipment.model.ShipmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentDTO {
    private Long id;

    private String orderId;

    private String pickupAddress;

    private String deliveryAddress;

    private LocalDateTime expectedDeliveryDate;

    private LocalDateTime deliveryDate;

    private ShipmentStatus status;
}
