package com.pms.productos.service;

import com.pms.productos.dto.ProductoDTO;
import com.pms.productos.model.Producto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductosService {

    List<ProductoDTO> getAllProducts();

    Optional<ProductoDTO> getProductById(Integer id);

    Producto saveProduct(ProductoDTO productDTO);
}
