package com.example.demo.shipment.service.mapper;

import com.example.demo.shipment.model.Shipment;
import com.example.demo.shipment.service.dto.ShipmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        uses = {}
)
public interface ShipmentMapper extends EntityMapper<ShipmentDTO, Shipment>{
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ShipmentDTO toDtoId(Shipment shipment);
}
