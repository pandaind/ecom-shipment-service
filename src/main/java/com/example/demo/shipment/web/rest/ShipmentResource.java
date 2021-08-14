package com.example.demo.shipment.web.rest;

import com.example.demo.shipment.service.ShipmentService;
import com.example.demo.shipment.service.dto.ShipmentDTO;
import com.example.demo.shipment.web.rest.errors.BadRequestAlertException;
import com.example.demo.shipment.web.rest.util.HeaderUtil;
import com.example.demo.shipment.web.rest.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
public class ShipmentResource {
    private static final String ENTITY_NAME = "Shipment";
    @Value("${spring.application.name}")
    private String applicationName;

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentResource(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }


    @GetMapping("/shipments/{orderId}")
    public ResponseEntity<ShipmentDTO> getShipment(@PathVariable("orderId") String orderId) {
        log.debug("REST request to get one shipment {}", orderId);
        var result = this.shipmentService.findShipment(orderId);
        return ResponseUtil.wrapNotFound(result);
    }

    @PostMapping("/shipments")
    public ResponseEntity<ShipmentDTO> addShipment(@RequestBody ShipmentDTO shipmentDTO) throws URISyntaxException {
        log.debug("REST request to save shipment {}", shipmentDTO);
        if (shipmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new shipment cannot already have an ID", ENTITY_NAME, "idexists");
        }

        ShipmentDTO result = this.shipmentService.addShipment(shipmentDTO);

        return ResponseEntity.created(new URI("/shipments" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/shipments/{orderId}")
    public ResponseEntity<ShipmentDTO> updateShipment(
            @PathVariable(value = "orderId", required = false) final String orderId,
            @RequestBody ShipmentDTO shipmentDTO) throws URISyntaxException {
        log.debug("REST request to update shipment {}", shipmentDTO);

        if (shipmentDTO.getId() == null && shipmentDTO.getOrderId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(orderId, shipmentDTO.getOrderId())
                && !Objects.equals(orderId, shipmentDTO.getOrderId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!this.shipmentService.existsByOrderId(orderId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShipmentDTO result = this.shipmentService.updateShipment(shipmentDTO);

        return ResponseEntity.created(new URI("/shipments" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getOrderId()))
                .body(result);
    }

}
