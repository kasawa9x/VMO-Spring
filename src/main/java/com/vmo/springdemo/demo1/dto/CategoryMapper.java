package com.vmo.springdemo.demo1.dto;

import com.vmo.springdemo.demo1.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO categoryToCategoryDTO (Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
