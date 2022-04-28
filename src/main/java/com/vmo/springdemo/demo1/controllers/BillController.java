package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.common.ApiResponse;
import com.vmo.springdemo.demo1.models.Bill;
import com.vmo.springdemo.demo1.models.Cart.AddToCart;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> placeOrder(Principal principal) throws MessagingException {
        User user = userRepository.findByUsername(principal.getName()).get();
        // place the order
        billService.saveBill(user);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("bill/history")
    public List<Bill> getBill(Principal principal) {
        Long user_íd = userRepository.findByUsername(principal.getName()).get().getId();
        System.out.println(user_íd);
        return billService.findBillByUserId(user_íd);
    }

    @GetMapping("bill/historyAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Bill> historyOrdeAdmin() {
        return billService.getAllBill();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id) {
        Bill bill = billService.getBillById(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}
