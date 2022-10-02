package com.example.demo.shipment.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ItemDTO {
    private Long id;

    private Long quantity;

    private BigDecimal subTotal;

    private ProductDTO product;
}
