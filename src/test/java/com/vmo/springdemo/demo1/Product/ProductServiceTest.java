package com.vmo.springdemo.demo1.Product;

import com.vmo.springdemo.demo1.exception.ProductNotFoundException;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.repository.ProductRepository;
import com.vmo.springdemo.demo1.service.impl.ProductServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Product> mockProducts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockProducts.add(new Product(i));
        }
        // 2. define behavior of Repository
        when(productRepository.findAll()).thenReturn(mockProducts);
        // 3. call service method
        List<Product> actualProducts = productService.getAllProduct();
        // 4. assert the result
        assertThat(actualProducts.size()).isEqualTo(mockProducts.size());
        // 4.1 ensure repository is called
        verify(productRepository).findAll();
    }
    @Test
    void whenGetInvalidOne_shouldThrowException() {
        Integer invalidProductId = 1;

        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> productService.getProductById(invalidProductId))
                .isInstanceOf(ProductNotFoundException.class);

        verify(productRepository).findById(any(Integer.class));
    }
}
