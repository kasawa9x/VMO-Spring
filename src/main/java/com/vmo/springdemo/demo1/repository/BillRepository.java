package com.vmo.springdemo.demo1.repository;

import com.vmo.springdemo.demo1.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findBillByUserId(@Param("user_id") long user_id);

}
