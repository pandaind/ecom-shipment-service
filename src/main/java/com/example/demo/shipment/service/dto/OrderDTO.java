package com.example.demo.shipment.service.dto;

import com.example.demo.shipment.model.EventStatus;
import com.example.demo.shipment.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderDTO {
    @JsonIgnore
    private Long id;
    private String orderId;
    private LocalDate orderDate;
    private OrderStatus status;
    private BigDecimal total;
    private List<ItemDTO> items;
    private UserDTO user;
    @JsonIgnore
    private EventStatus eventStatus = EventStatus.NEW;
}
