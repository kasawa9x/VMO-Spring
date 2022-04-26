package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.models.BillDetail;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    UserRepository userRepository;

    //view bill Details
    @GetMapping("billdetail/{id}")
    public ResponseEntity<BillDetail> billDetail(@PathVariable int billId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();

        List<BillDetail> billDetail = billDetailService.findBillDetailByBillId(billId);
        return new ResponseEntity<BillDetail>((BillDetail) billDetail, HttpStatus.OK);

    }
}
