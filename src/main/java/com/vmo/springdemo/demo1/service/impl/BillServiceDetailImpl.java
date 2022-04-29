package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.models.BillDetail;
import com.vmo.springdemo.demo1.repository.BillDetailRepository;
import com.vmo.springdemo.demo1.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class BillServiceDetailImpl implements BillDetailService {
    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> getAllBillDetail() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail updateBillDetail(BillDetail billDetail) {
        BillDetail existingBillDetail = billDetailRepository.findById(billDetail.getId()).orElse(null);
        existingBillDetail.setQuantity(billDetail.getQuantity());
        existingBillDetail.setPrice(billDetail.getPrice());
        existingBillDetail.setProduct(billDetail.getProduct());
        return billDetailRepository.save(existingBillDetail);
    }

    @Override
    public BillDetail saveBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }


    @Override
    public BillDetail getBillDetailById(int id) {
        return billDetailRepository.getById(id);
    }

    @Override
    public List<BillDetail> findBillDetailByBillId(int id) {
        return billDetailRepository.listBillDetailByBillId(id);
    }

    @Override
    public long totalMoney(int billId) throws SQLException {
        return billDetailRepository.totalMoney(billId);
    }
}
