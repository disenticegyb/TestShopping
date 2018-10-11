package com.gyb.shop.service.Impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyb.shop.pojo.Category;
import com.gyb.shop.pojo.Product;
import com.gyb.shop.service.OrderItemService;
import com.gyb.shop.service.ProductImageService;
import com.gyb.shop.service.ProductService;
import com.gyb.shop.service.ReviewService;
/**
 * 继承BeanService实现类，实现ProductService
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService{
	
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
    
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);//遍历分类取出每个分类中所有的产品集合；
        }
    }
 
    @Override
    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;//设置产品每行的数量；
        for (Category category : categorys) {//遍历分类
            List<Product> products =  category.getProducts();//获取分类产品集合
            List<List<Product>> productsByRow =  new ArrayList<>();//创建产品信息结合
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {//遍历对象集合的长度，每次增加对应长度
                int size = i+productNumberEachRow;//size赋值，每次增加对应的行数；
                size= size>products.size()?products.size():size;//三目运算；判断如果size大于集合长度，则按照产品集合长度赋值，否则为size
                List<Product> productsOfEachRow =products.subList(i, size);//获取products集合中的信息；从i到size；
                productsByRow.add(productsOfEachRow);//分行存入集合；
            }
             
            category.setProductsByRow(productsByRow);
        }
    }
     
    @Override
    public void fill(Category category) {
        List<Product> products= listByParent(category);//查询父类分类下的产品集合；
        
        for (Product product : products)//遍历产品集合
            productImageService.setFirstProdutImage(product);//获得产品名下的图片，并赋值给产品属性；
         
        category.setProducts(products);//将产品列表赋值给category中的products；
         
    }

	@Override
	public void setSaleAndReviewNumber(Product product) {
		int saleCount = orderItemService.total(product);//查询销售数量
		product.setSaleCount(saleCount);
        int reviewCount = reviewService.total(product);//查询评论数量
        product.setReviewCount(reviewCount);
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		 for (Product product : products) {//集合遍历
	            setSaleAndReviewNumber(product);
	        }
	}

	@Override
	public List<Product> search(String keyword, int start, int count) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.like("name", "%"+keyword+"%"));
        return findByCriteria(dc,start,count);
	} 

	
}
