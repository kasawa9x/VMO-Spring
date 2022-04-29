package com.vmo.springdemo.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;

    private String name;

}
