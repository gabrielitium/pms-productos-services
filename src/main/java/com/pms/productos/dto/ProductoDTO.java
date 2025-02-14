package com.pms.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductoDTO {

    private Integer idProducto;
    private String clave;
    private String descripcion;
    private Double precio;

}