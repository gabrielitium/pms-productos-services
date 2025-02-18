package com.pms.productos.tes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.pms.productos.dto.ProductoDTO;
import com.pms.productos.model.Producto;
import com.pms.productos.repository.ProductoRepository;
import com.pms.productos.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private ProductoDTO productoDTO;

    @BeforeEach
    void setUp() {
        producto = new Producto("P001", "Producto Test", 100.0);
        producto.setId(1);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaActualizacion(LocalDateTime.now());

        productoDTO = new ProductoDTO(1, "P001", "Producto Test", 100.0);
    }

    @Test
    void shouldReturnAllProducts() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoDTO> products = productoService.getAllProducts();

        assertThat(products).hasSize(1);
        assertThat(products.get(0).getIdProducto()).isEqualTo(producto.getId());
        assertThat(products.get(0).getClave()).isEqualTo(producto.getClave());

        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnProductById() {
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Optional<ProductoDTO> foundProduct = productoService.getProductById(1);

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getIdProducto()).isEqualTo(producto.getId());
        assertThat(foundProduct.get().getClave()).isEqualTo(producto.getClave());

        verify(productoRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        Optional<ProductoDTO> foundProduct = productoService.getProductById(99);

        assertThat(foundProduct).isEmpty();

        verify(productoRepository, times(1)).findById(99);
    }

    @Test
    void shouldSaveProductSuccessfully() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto savedProduct = productoService.saveProduct(productoDTO);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getClave()).isEqualTo(productoDTO.getClave());
        assertThat(savedProduct.getDescripcion()).isEqualTo(productoDTO.getDescripcion());

        verify(productoRepository, times(1)).save(any(Producto.class));
    }
}

