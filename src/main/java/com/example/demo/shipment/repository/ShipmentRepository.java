package com.example.demo.shipment.repository;

import com.example.demo.shipment.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByOrderId(String orderId);
    boolean existsByOrderId(String orderId);
}
