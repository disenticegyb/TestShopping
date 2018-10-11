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
 * �̳�BeanServiceʵ���࣬ʵ��ProductService
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
            fill(category);//��������ȡ��ÿ�����������еĲ�Ʒ���ϣ�
        }
    }
 
    @Override
    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;//���ò�Ʒÿ�е�������
        for (Category category : categorys) {//��������
            List<Product> products =  category.getProducts();//��ȡ�����Ʒ����
            List<List<Product>> productsByRow =  new ArrayList<>();//������Ʒ��Ϣ���
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {//�������󼯺ϵĳ��ȣ�ÿ�����Ӷ�Ӧ����
                int size = i+productNumberEachRow;//size��ֵ��ÿ�����Ӷ�Ӧ��������
                size= size>products.size()?products.size():size;//��Ŀ���㣻�ж����size���ڼ��ϳ��ȣ����ղ�Ʒ���ϳ��ȸ�ֵ������Ϊsize
                List<Product> productsOfEachRow =products.subList(i, size);//��ȡproducts�����е���Ϣ����i��size��
                productsByRow.add(productsOfEachRow);//���д��뼯�ϣ�
            }
             
            category.setProductsByRow(productsByRow);
        }
    }
     
    @Override
    public void fill(Category category) {
        List<Product> products= listByParent(category);//��ѯ��������µĲ�Ʒ���ϣ�
        
        for (Product product : products)//������Ʒ����
            productImageService.setFirstProdutImage(product);//��ò�Ʒ���µ�ͼƬ������ֵ����Ʒ���ԣ�
         
        category.setProducts(products);//����Ʒ�б�ֵ��category�е�products��
         
    }

	@Override
	public void setSaleAndReviewNumber(Product product) {
		int saleCount = orderItemService.total(product);//��ѯ��������
		product.setSaleCount(saleCount);
        int reviewCount = reviewService.total(product);//��ѯ��������
        product.setReviewCount(reviewCount);
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		 for (Product product : products) {//���ϱ���
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
