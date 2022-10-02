package com.example.demo.shipment.event.order;

import com.example.demo.shipment.service.ShipmentService;
import com.example.demo.shipment.service.dto.OrderDTO;
import com.example.demo.shipment.service.dto.ShipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProcessor {
    private final ShipmentService shipmentService;


    @Autowired
    public OrderEventProcessor(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }


    public void processOrderEvent(OrderEvent event){
        switch (event.getType()){
            case NEW -> shipmentService.addShipment(shipmentFromOrderEvent(event));
            case UPDATE -> shipmentService.updateShipment(shipmentFromOrderEvent(event));
            case DELETE -> shipmentService.removeShipment(event.getOrder().getOrderId());
            default -> {

            }
        }
    }

    private ShipmentDTO shipmentFromOrderEvent(OrderEvent event){
        ShipmentDTO shipment = new ShipmentDTO();
        OrderDTO orderDTO = event.getOrder();
        shipment.setOrderId(orderDTO.getOrderId());
        return shipment;
    }
}
