package com.example.demo.shipment.web.rest;

import com.example.demo.shipment.service.ShipmentService;
import com.example.demo.shipment.service.dto.ShipmentDTO;
import com.example.demo.shipment.web.rest.errors.BadRequestAlertException;
import com.example.demo.shipment.web.rest.util.HeaderUtil;
import com.example.demo.shipment.web.rest.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
@RefreshScope
public class ShipmentResource {
    private static final String ENTITY_NAME = "Shipment";
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${refresh.value}")
    private String refreshValue;

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

    @GetMapping("/shipments")
    public ResponseEntity<List<ShipmentDTO>> getShipment() {
        log.debug("REST request to get shipments");
        var shipments = this.shipmentService.findAll();
        return ResponseEntity.ok().body(shipments);
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

    @GetMapping("/shipments/value")
    public ResponseEntity<String> getRefreshValue() {
        return ResponseEntity.ok(refreshValue);
    }
}
