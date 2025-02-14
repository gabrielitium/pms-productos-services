package com.pms.productos.service;

import com.pms.productos.dto.ProductoDTO;
import com.pms.productos.model.Producto;
import com.pms.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductoRepository productRepository;

    public ProductService(ProductoRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductoDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductoDTO(p.getClave(), p.getDescripcion(), p.getPrecio()))
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(p -> new ProductoDTO(p.getClave(), p.getDescripcion(), p.getPrecio()));
    }

    public Producto saveProduct(ProductoDTO productDTO) {
        Producto product = new Producto(productDTO.getClave(), productDTO.getDescripcion(), productDTO.getPrecio());
        product.setFechaCreacion(LocalDateTime.now());
        product.setFechaActualizacion(LocalDateTime.now());
        return productRepository.save(product);
    }
}