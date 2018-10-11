package com.gyb.shop.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gyb.shop.service.CategoryService;
import com.gyb.shop.service.OrderItemService;
import com.gyb.shop.service.OrderService;
import com.gyb.shop.service.ProductImageService;
import com.gyb.shop.service.ProductService;
import com.gyb.shop.service.PropertyService;
import com.gyb.shop.service.PropertyValueService;
import com.gyb.shop.service.ReviewService;
import com.gyb.shop.service.UserService;

/**
 * ����Action4Service�����ṩ����ע��
 * @author disentice
 *
 */
public class Action4Service extends Action4Pojo{

	@Autowired
    CategoryService categoryService;
	
	@Autowired
	PropertyService propertyService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
    @Autowired
    PropertyValueService propertyValueService; 
    
    @Autowired
    UserService userService;
    
    @Autowired
    OrderService orderService;
    
    @Autowired
	OrderItemService orderItemService;
    
    @Autowired
    ReviewService reviewService;
    
	//transient to persistent ˲ʱ����ת��Ϊ�־ö���
    public void t2p(Object o){
            try {
                Class clazz = o.getClass();//ͨ�������ȡ�����
                int id = (Integer) clazz.getMethod("getId").invoke(o);//ͨ������getid������ȡ����id��
                Object persistentBean = categoryService.get(clazz, id);//ͨ��id��ȡ�־û�����
                String beanName = clazz.getSimpleName();//��ȡ�����ȫ·��
                Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);//��ȡ�����set�������̳���Action4pojo��
                setMethod.invoke(this, persistentBean);//����һ���־û�����
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
