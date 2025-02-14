package com.pms.productos.controller;

import com.pms.productos.dto.ProductoDTO;
import com.pms.productos.model.Producto;
import com.pms.productos.service.ProductosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    private final ProductosService productoService;

    public ProductosController(ProductosService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> getAllProducts() {
        return productoService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductById(@PathVariable Integer id) {
        return productoService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto createProduct(@RequestBody ProductoDTO productDTO) {
        return productoService.saveProduct(productDTO);
    }
}