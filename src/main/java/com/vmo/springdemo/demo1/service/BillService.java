package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.Bill;
import com.vmo.springdemo.demo1.models.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface BillService {
    List<Bill> getAllBill();

    void saveBill(User user) throws MessagingException;

    String removeBillById(int id);

    Bill getBillById(int id);

    List<Bill> findBillByUserId(long userId);

}
