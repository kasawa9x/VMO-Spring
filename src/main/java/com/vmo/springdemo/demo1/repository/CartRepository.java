package com.vmo.springdemo.demo1.repository;

import com.vmo.springdemo.demo1.models.Cart.Cart;
import com.vmo.springdemo.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    List<Cart> findAllByUser(User user);

    List<Cart> deleteByUser(User user);
}
