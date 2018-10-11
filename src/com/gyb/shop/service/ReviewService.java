package com.gyb.shop.service;

import com.gyb.shop.pojo.Order;
import com.gyb.shop.pojo.Review;

public interface ReviewService extends BaseService{
	void saveReviewAndUpdateOrderStatus(Review review, Order order);

}
