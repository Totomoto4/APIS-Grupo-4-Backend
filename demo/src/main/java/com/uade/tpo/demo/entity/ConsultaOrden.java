package com.uade.tpo.demo.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ConsultaOrden {
    private HashMap<Long, Integer> productos;
    private List<String> codigos;
}
