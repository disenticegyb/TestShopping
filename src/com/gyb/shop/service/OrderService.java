package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Order;
import com.gyb.shop.pojo.OrderItem;
import com.gyb.shop.pojo.User;

public interface OrderService extends BaseService{

	public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete"; 
    //������������
    public float createOrder(Order order, List<OrderItem> ois);
    //������ѯ
    public List<Order> listByUserWithoutDelete(User user);
}
