package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.exception.ProductNotFoundException;
import com.vmo.springdemo.demo1.models.Bill;
import com.vmo.springdemo.demo1.models.BillDetail;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.Cart.CartItem;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.BillRepository;
import com.vmo.springdemo.demo1.repository.ProductRepository;
import com.vmo.springdemo.demo1.service.CartService;
import com.vmo.springdemo.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());

//        String imageUUID;
//        if(!fileProductImage.isEmpty()){
//            imageUUID = fileProductImage.getOriginalFilename();
//            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
//            try {
//                Files.write(fileNameAndPath, fileProductImage.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else {
//            imageUUID = imgName;
//        }//save image
//        existingProduct.setImageName(product.setImageName(imageUUID));
        return productRepository.save(existingProduct);
    }

    @Override
    public String removeProductById(int id) {
        productRepository.deleteById(id);
        return "product removed !! " + id;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void reductionQuantity( User user) {
        CartDto cartDto = cartService.listCartItem(user);

        List<CartItem> cartItemList = cartDto.getcartItems();

        for (CartItem cartItem :
                cartItemList) {
            // find product by product id and redution quantity when create order
            Product productl = productRepository.findById(cartItem.getProduct().getId()).get();
            productl.setQuantity(productl.getQuantity() - cartItem.getQuantity());

            productRepository.save(productl);
        }

    }

    @Override
    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }
}
