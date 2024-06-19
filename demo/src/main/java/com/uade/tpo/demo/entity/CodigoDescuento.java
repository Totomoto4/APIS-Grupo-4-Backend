package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CODIGOS")
public class CodigoDescuento {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descuento")
    private float descuento;

    @Column(name = "disponibles")
    private int disponibles;
}
