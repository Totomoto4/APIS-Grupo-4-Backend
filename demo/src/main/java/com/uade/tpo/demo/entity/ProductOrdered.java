package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_ordered")
>>>>>>> Stashed changes
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ORDERED_ID")
    private long productOrderedId;

    @Column(name = "PRODUCT_ORDERED_ORDER_ID")
    private long orderId;

    @Column(name = "PRODUCT_ORDERED_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_ORDERED_PRICE", nullable = false)
    private float price;

    @Min(value = 0, message = "El valor pedido no puede ser menor a 0")
    @Column(name = "PRODUCT_ORDERED_QUANTITY", nullable = false)
    private int quantity;


    public ProductOrdered(long orderId, String name, float price, int quantity) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
