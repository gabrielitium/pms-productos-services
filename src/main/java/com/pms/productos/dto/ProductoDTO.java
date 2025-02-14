package com.pms.productos.dto;

import lombok.Getter;

@Getter
public class ProductoDTO {
    private String clave;
    private String descripcion;
    private Double precio;

    public ProductoDTO(String clave, String descripcion, Double precio) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}