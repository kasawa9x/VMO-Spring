package com.vmo.springdemo.demo1.repository;

import com.vmo.springdemo.demo1.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
    List<BillDetail> findBillDetailByBillId(@Param("bill_id") int bill_id);

    @Query(value = "SELECT * FROM billdetail_tbl WHERE bill_id =  ?1", nativeQuery = true)
    List<BillDetail> listBillDetailByBillId(@Param("bill_id") int bill_id);

    @Query(value = "SELECT SUM(price) FROM bill_detail WHERE bill_id =  ?1", nativeQuery = true)
    long totalMoney(int billId) throws SQLException;
}
