package com.gyb.shop.comparator;

import java.util.Comparator;

import com.gyb.shop.pojo.Product;

/**
 * 根据创建日期排序，新创建在前
 * @author disentice
 *
 */
public class ProductDateComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p1.getCreateDate().compareTo(p2.getCreateDate());
	}

}
