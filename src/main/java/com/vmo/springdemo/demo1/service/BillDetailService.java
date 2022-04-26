package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.BillDetail;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public interface BillDetailService {
    List<BillDetail> getAllBillDetail();

    BillDetail updateBillDetail(BillDetail billDetail);

    BillDetail saveBillDetail(BillDetail billDetail);

    BillDetail getBillDetailById(int id);

    List<BillDetail> findBillDetailByBillId (int id);

    long totalMoney(int billId) throws SQLException;

}
