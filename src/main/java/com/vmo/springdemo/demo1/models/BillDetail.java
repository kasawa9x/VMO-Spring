package com.vmo.springdemo.demo1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    Bill bill;
}
