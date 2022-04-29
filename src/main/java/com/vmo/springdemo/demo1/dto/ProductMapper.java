package com.vmo.springdemo.demo1.dto;

import com.vmo.springdemo.demo1.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO productToProductDTO (Product product);
    Product productDTOToProduct(ProductDTO productDTO);
    List<ProductDTO> productsToProductDTOS(List<Product> products);

}
