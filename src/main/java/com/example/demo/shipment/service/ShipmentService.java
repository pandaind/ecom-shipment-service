package com.example.demo.shipment.service;

import com.example.demo.shipment.service.dto.ShipmentDTO;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    Optional<ShipmentDTO> findShipment(String orderId);

    List<ShipmentDTO> findAll();
    ShipmentDTO updateShipment(ShipmentDTO shipment);
    ShipmentDTO addShipment(ShipmentDTO shipment);
    boolean existsByOrderId(String orderId);
    void removeShipment(String orderId);
}
