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
    //������
    //������Ͳ�Ʒ���һ���������������Զ�Ӧһ����Ʒ��
    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;
    
    //������Ͷ����Ƕ��һ�����������������һ������
    @ManyToOne
    @JoinColumn(name="oid")   
    private Order order;
    //��������û��Ƕ��һ�������û����Զ�Ӧ��������
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