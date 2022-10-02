package com.example.demo.shipment.service.impl;

import com.example.demo.shipment.model.Shipment;
import com.example.demo.shipment.model.ShipmentStatus;
import com.example.demo.shipment.repository.ShipmentRepository;
import com.example.demo.shipment.service.ShipmentService;
import com.example.demo.shipment.service.dto.ShipmentDTO;
import com.example.demo.shipment.service.mapper.ShipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repository;
    private final ShipmentMapper mapper;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository repository, ShipmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShipmentDTO> findShipment(String orderId) {
        return this.repository.findByOrderId(orderId)
                .map(mapper::toDto);
    }

    @Override
    public List<ShipmentDTO> findAll() {
        return this.mapper.toDto(this.repository.findAll());

    }

    @Override
    public ShipmentDTO updateShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = this.mapper.toEntity(shipmentDTO);
        shipment = this.repository.save(shipment);
        return this.mapper.toDto(shipment);
    }

    @Override
    public ShipmentDTO addShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = this.mapper.toEntity(shipmentDTO);
        shipment.setStatus(ShipmentStatus.NEW);
        shipment = this.repository.save(shipment);
        return this.mapper.toDto(shipment);
    }

    @Override
    public boolean existsByOrderId(String orderId) {
        return this.repository.existsByOrderId(orderId);
    }

    @Override
    public void removeShipment(String orderId) {
        this.repository.findByOrderId(orderId).ifPresent(this.repository::delete);
    }
}
