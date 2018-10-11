package com.gyb.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gyb.shop.pojo.Order;
import com.gyb.shop.pojo.Review;
import com.gyb.shop.service.OrderService;
import com.gyb.shop.service.ReviewService;

@Service
public class ReviewServiceImpl extends BaseServiceImpl implements ReviewService{

	@Autowired 
	OrderService orderService;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public void saveReviewAndUpdateOrderStatus(Review review, Order order) {
		orderService.update(order);
		save(review);
	}

}
