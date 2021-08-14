package com.example.demo.shipment.service;

import com.example.demo.shipment.service.dto.ShipmentDTO;

import java.util.Optional;

public interface ShipmentService {
    Optional<ShipmentDTO> findShipment(String orderId);
    ShipmentDTO updateShipment(ShipmentDTO shipment);
    ShipmentDTO addShipment(ShipmentDTO shipment);
    boolean existsByOrderId(String orderId);
}
