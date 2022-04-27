package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.models.BillDetail;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    UserRepository userRepository;

    //view bill Details
    @GetMapping("/billdetail/{billId}")
    public List<BillDetail> billDetail(@PathVariable("billId") Integer billId) {
        List<BillDetail> billDetail =  billDetailService.findBillDetailByBillId(billId);
        return billDetail;
    }
}
