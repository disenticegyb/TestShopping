package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Order;

public interface OrderItemService extends BaseService{

	 public void fill(List<Order> orders);
	 public void fill(Order order);
}
