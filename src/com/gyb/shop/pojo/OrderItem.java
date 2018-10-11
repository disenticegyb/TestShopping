package com.gyb.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //订单项
    //订单项和产品多对一，及多个订单项可以对应一个产品；
    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;
    
    //订单项和订单是多对一，及多个订单项生成一个订单
    @ManyToOne
    @JoinColumn(name="oid")   
    private Order order;
    //订单项和用户是多对一，单个用户可以对应多个订单项；
    @ManyToOne
    @JoinColumn(name="uid")   
    private User user;
     
    private int number;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
 
}