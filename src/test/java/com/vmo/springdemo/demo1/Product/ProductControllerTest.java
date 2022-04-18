package com.vmo.springdemo.demo1.Product;

import com.vmo.springdemo.demo1.controllers.ProductController;
import com.vmo.springdemo.demo1.models.Category;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.service.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(ProductController.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ProductControllerTest {
    @MockBean
    MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    ProductService productService;
    private List<Product> allProduct;
    private Product product1;
    private Product product2;
    private Category category;
    @Before
    public void setup(){
        category = new Category(1,"Smart Watch");
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        product1 = new Product(1,"Apple Watch 1",5, 300, category);
        product2 = new Product(2,"Apple Watch 2",10, 400, category);
        allProduct.add(product1);
        allProduct.add(product2);
    }
    @WithMockUser(value = "ROLE_ADMIN", roles = {"ROLE_ADMIN"})
    @Test
    public void testFindAll() throws Exception {
//        List<Product> allProduct = IntStream.range(0, 10)
//                .mapT  oObj(i -> new Product(i))
//                .collect(Collectors.toList());

        given(productService.getAllProduct()).willReturn(allProduct);

        mvc.perform(get("/api/product").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2))); // Hi vọng server trả về List độ dài 10
//                .andExpect((ResultMatcher) jsonPath("$[0].id", is(0))) // Hi vọng phần tử trả về đầu tiên có id = 0
//                .andExpect((ResultMatcher) jsonPath("$[0].", is(""))) //
//                .andExpect((ResultMatcher) jsonPath("$[0].", is("")));
    }
}
