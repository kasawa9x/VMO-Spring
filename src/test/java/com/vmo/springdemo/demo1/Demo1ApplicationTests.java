package com.vmo.springdemo.demo1;

import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.repository.ProductRepository;
import com.vmo.springdemo.demo1.service.ProductService;
import com.vmo.springdemo.demo1.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class Demo1ApplicationTests {
//    @Mock
//    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @Test
    void contextLoads() {
    }
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
}
