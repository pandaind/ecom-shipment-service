package com.example.demo.shipment.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String orderId;

    private String pickupAddress;

    private String deliveryAddress;

    private LocalDateTime expectedDeliveryDate;

    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shipment shipment = (Shipment) o;

        return Objects.equals(id, shipment.id);
    }

    @Override
    public int hashCode() {
        return 1893601392;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "orderId = " + orderId + ", " +
                "pickupAddress = " + pickupAddress + ", " +
                "deliveryAddress = " + deliveryAddress + ", " +
                "status = " + status + ")";
    }
}
