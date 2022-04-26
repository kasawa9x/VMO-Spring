package com.vmo.springdemo.demo1.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BILL_TBL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int id;
    private Date createdDate;


    private long price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;



    public Bill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
