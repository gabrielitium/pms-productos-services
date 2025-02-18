package com.pms.productos.service.impl;

import com.pms.productos.dto.ProductoDTO;
import com.pms.productos.model.Producto;
import com.pms.productos.repository.ProductoRepository;
import com.pms.productos.service.ProductosService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductosService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> getAllProducts() {
        return productoRepository.findAll().stream()
                .map(p -> new ProductoDTO(p.getId(), p.getClave(), p.getDescripcion(), p.getPrecio()))
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductById(Integer id) {
        return productoRepository.findById(id)
                .map(p -> new ProductoDTO(p.getId(), p.getClave(), p.getDescripcion(), p.getPrecio()));
    }

    @Transactional
    public Producto saveProduct(ProductoDTO productDTO) {
        Producto producto = new Producto(
                productDTO.getClave(),
                productDTO.getDescripcion(),
                productDTO.getPrecio());
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaActualizacion(LocalDateTime.now());
        return productoRepository.save(producto);
    }
}