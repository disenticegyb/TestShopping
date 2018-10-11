package com.gyb.shop.comparator;

import java.util.Comparator;

import com.gyb.shop.pojo.Product;

/**
 * �������۶������򣬶����ǰ
 * @author disentice
 *
 */
public class ProductReviewComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount()-p1.getReviewCount();
	}

}
