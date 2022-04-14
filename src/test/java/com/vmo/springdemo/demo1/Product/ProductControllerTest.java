package com.vmo.springdemo.demo1.Product;

import com.vmo.springdemo.demo1.controllers.ProductController;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;
    @Test
    public void testFindAll() throws Exception {
        List<Product> allProduct = IntStream.range(0, 10)
                .mapToObj(i -> new Product(i))
                .collect(Collectors.toList());

        // giả lập todoService trả về List mong muốn
        given(productService.getAllProduct()).willReturn(allProduct);

        mvc.perform(get("/api/product").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                .andExpect((ResultMatcher) jsonPath("$", hasSize(10))) // Hi vọng server trả về List độ dài 10
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(0))) // Hi vọng phần tử trả về đầu tiên có id = 0
                .andExpect((ResultMatcher) jsonPath("$[0].title", is(""))) //
                .andExpect((ResultMatcher) jsonPath("$[0].detail", is("")));
    }
}
