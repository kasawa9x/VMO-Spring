package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.models.Bill;
import com.vmo.springdemo.demo1.models.BillDetail;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.Cart.CartItem;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.BillDetailRepository;
import com.vmo.springdemo.demo1.repository.BillRepository;
import com.vmo.springdemo.demo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private MailService mailService;

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public void saveBill(User user) throws MessagingException {
        CartDto cartDto = cartService.listCartItem(user);

        List<CartItem> cartItemList = cartDto.getcartItems();
        // create the bill and save it
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setPrice((long) cartDto.getTotalCost());
        bill.setCreatedDate(new Date());
        billRepository.save(bill);
        System.out.println(bill);

        for (CartItem cartItem :
                cartItemList) {
            // create billdetail and save each one
            BillDetail billDetail = new BillDetail();
            billDetail.setProduct(cartItem.getProduct());
            billDetail.setPrice((long) cartItem.getProduct().getPrice());
            billDetail.setQuantity(cartItem.getQuantity());
            billDetail.setBill(bill);

            billDetailRepository.save(billDetail);
        }

        productService.reductionQuantity(user);
        cartService.deleteUserCartItems(user);
        mailService.sendMail(user);
    }

    @Override
    public String removeBillById(int id) {
        billRepository.deleteById(id);
        return "bill remove" + id;
    }

    @Override
    public Bill getBillById(int id) {
        return billRepository.getById(id);
    }

    @Override
    public List<Bill> findBillByUserId(long userId) {
        List<Bill> billList = billRepository.findBillByUserId(userId);
        return billList;
    }
}
