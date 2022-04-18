package com.vmo.springdemo.demo1.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "BILLDETAIL_TBL")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billdetail_id")
    int id;
    int quantity;
    long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    Bill bill;
}
