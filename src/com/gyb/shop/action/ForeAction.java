package com.gyb.shop.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.math.RandomUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.gyb.shop.comparator.ProductAllComparator;
import com.gyb.shop.comparator.ProductDateComparator;
import com.gyb.shop.comparator.ProductPriceComparator;
import com.gyb.shop.comparator.ProductReviewComparator;
import com.gyb.shop.comparator.ProductSaleCountComparator;
import com.gyb.shop.pojo.OrderItem;
import com.gyb.shop.pojo.Product;
import com.gyb.shop.pojo.User;
import com.gyb.shop.service.OrderService;
import com.gyb.shop.service.ProductImageService;
import com.opensymphony.xwork2.ActionContext;

/**
 * ����ǰ����Ҫ�������Ϣ�Ƚ��ӣ��в�Ʒ�����ࡢͼƬ���û���Ϣ�� �ʴ���һ��ר�Ŵ���ǰ�������action��
 * 
 * @author disentice
 *
 */
public class ForeAction extends Action4Result {

	// ��Ҫ���ݲ����������ò���msg��
	// ���ź�������������Խ��Խ�࣬�ƻ�action�䵱�������Ľṹ��
	// ������Action4Parameter�࣬ר�Ž��в����Ĵ���ͨ���̳�ʵ�ֻ�ȡ
//	String msg;

	// ��½
	@Action("forelogin")
	public String login() {
		// ���˺Ž���ת�塪����ֹ����ע�᣻
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		// �����˺������ȡ����uuser_session
		User user_session = userService.get(user.getName(), user.getPassword());
		if (null == user_session) {// Ϊ��ȡ����ת��½����
			msg = "�˺��������";
			return "login.jsp";
		}
		// ��ȡ�򽫶��󱣴���session��
		ActionContext.getContext().getSession().put("user", user_session);
		return "homePage";
	}

	// ��½�ж�
	@Action("foreregister")
	public String register() {// ��½�ж�
		user.setName(HtmlUtils.htmlEscape(user.getName()));// �����ȡname����ת�壻
		boolean exist = userService.isExist(user.getName());// ������

		if (exist) {
			msg = "�û����Ѿ���ʹ��,����ʹ��";
			return "register.jsp"; // ���ص�½����
		}
		userService.save(user);// �����ڱ���user�����ݿ�
		return "registerSuccessPage";// ���ص�½�ɹ�����
	}

	// ��ѯ��ҳ��Ҫ��ʾ����Ϣ
	@Action("forehome")
	public String home() {
		categorys = categoryService.list();// ��ȡ���з���
		productService.fill(categorys);// �����������Ʒ����
		productService.fillByRow(categorys);// ��������������Ƽ���Ʒ����
		return "home.jsp";
	}

	// �˳�
	@Action("forelogout")
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return "homePage";
	}

	// ��Ʒҳ����ʾ
	@Action("foreproduct")
	public String product() {
		t2p(product);// �־û�����

		productImageService.setFirstProdutImage(product);// ���õ�����Ƭ
		//��ȡ��ƷչʾͼƬ
		productSingleImages = productImageService.list("product", product, "type", ProductImageService.type_single);
		//��ȡ��Ʒ����ͼƬ
		productDetailImages = productImageService.list("product", product, "type", ProductImageService.type_detail);
		//��ֵ
		product.setProductSingleImages(productSingleImages);
		product.setProductDetailImages(productDetailImages);
		//��ȡ��Ʒ����ֵ����
		propertyValues = propertyValueService.listByParent(product);
		//��ȡ���ۼ���
		reviews = reviewService.listByParent(product);
		//������������������
		productService.setSaleAndReviewNumber(product);
		return "product.jsp";
	}
	
	//�ж��Ƿ�Ϊ��½״̬
	@Action("forecheckLogin")
	public String checkLogin() {
	    User u =(User) ActionContext.getContext().getSession().get("user");//��session�л�ȡ����user
	    if(null==u)
	        return "fail.jsp";
	    else
	        return "success.jsp";
	}
	
	//·��/foreloginAjax��½����
	@Action("foreloginAjax")
	public String loginAjax() {
	     
	    user.setName(HtmlUtils.htmlEscape(user.getName()));
	    User user_session = userService.get(user.getName(),user.getPassword());
	       
	    if(null==user_session)
	        return "fail.jsp";
	     
	    ActionContext.getContext().getSession().put("user", user_session);
	    return "success.jsp";      
	}
	
	//��Ʒ����
	@Action("forecategory")
	public String category(){
	    t2p(category);//�־û�����
	    productService.fill(category);//�������Ͳ�Ʒ
	    productService.setSaleAndReviewNumber(category.getProducts());//��ȡ��Ʒ����������      
	      
	    if(null!=sort){//�ж���������Ƿ�Ϊ��
	    switch(sort){//
	        case "review"://��������
	            Collections.sort(category.getProducts(),new ProductReviewComparator());
	            break;
	        case "date" ://���ݴ�������
	            Collections.sort(category.getProducts(),new ProductDateComparator());
	            break;
	        case "saleCount" ://��������
	            Collections.sort(category.getProducts(),new ProductSaleCountComparator());
	            break;
	        case "price"://���ݼ۸�
	            Collections.sort(category.getProducts(),new ProductPriceComparator());
	            break;
	        case "all"://��������*����
	            Collections.sort(category.getProducts(),new ProductAllComparator());
	            break;
	        }
	    }
	    return "category.jsp";         
	}
	
	//ģ����ѯ���ؽ��
	@Action("foresearch")
	public String search(){
	    products= productService.search(keyword,0,20);//���ݹؼ��ַ���ǰ20������
	    productService.setSaleAndReviewNumber(products);//�������ۺ�����
	    for (Product product : products)
	        productImageService.setFirstProdutImage(product);  //����ͼƬ
	     
	    return "searchResult.jsp";
	}
	
	//��������
	@Action("forebuyone")
	public String buyone() {
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    boolean found = false;//����еĻ�����������num��
	    List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	    for (OrderItem oi : ois) {
	        if(oi.getProduct().getId()==product.getId()){
	            oi.setNumber(oi.getNumber()+num);
	            orderItemService.update(oi);
	            found = true;
	            oiid = oi.getId();//��ȡid
	            break;
	        }
	    }        
	    if(!found){//��������û���ҵ��������µĶ�����
	        OrderItem oi = new OrderItem();
	        oi.setUser(user);
	        oi.setNumber(num);
	        oi.setProduct(product);
	        orderItemService.save(oi);
	        oiid = oi.getId();//��ȡid
	    }
	    return "buyPage";
	}
	
	//����ҳ��
	@Action("forebuy")
	public String buy() {
	    orderItems = new ArrayList<>();
	    for (int oiid : oiids) {
	        OrderItem oi= (OrderItem) orderItemService.get(oiid);
	        total +=oi.getProduct().getPromotePrice()*oi.getNumber();
	        orderItems.add(oi);
	        productImageService.setFirstProdutImage(oi.getProduct());
	    }
	      
	    ActionContext.getContext().getSession().put("orderItems", orderItems);
	    return "buy.jsp";
	}
	
	//���Ӷ�����
	@Action("foreaddCart")
	public String addCart() {
	     
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    boolean found = false;
	 
	    List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	    for (OrderItem oi : ois) {
	        if(oi.getProduct().getId()==product.getId()){
	            oi.setNumber(oi.getNumber()+num);
	            orderItemService.update(oi);
	            found = true;
	            break;
	        }
	    }      
	      
	    if(!found){
	        OrderItem oi = new OrderItem();
	        oi.setUser(user);
	        oi.setNumber(num);
	        oi.setProduct(product);
	        orderItemService.save(oi);
	    }
	    return "success.jsp";
	}
	
	//���ﳵ
	@Action("forecart")
	public String cart() {
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    orderItems = orderItemService.list("user",user,"order", null);
	    for (OrderItem orderItem : orderItems)
	        productImageService.setFirstProdutImage(orderItem.getProduct());
	    return "cart.jsp";
	}
	
	//��������������
	 @Action("forechangeOrderItem")
	 public String changeOrderItem() {
	     User user =(User) ActionContext.getContext().getSession().get("user");
	     List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	     for (OrderItem oi : ois) {
	         if(oi.getProduct().getId()==product.getId()){
	             oi.setNumber(num);
	             orderItemService.update(oi);
	             break;
	         }   
	     }      
	     return "success.jsp";
	 } 
	 
	 //ȷ��ɾ��
	 @Action("foredeleteOrderItem")
	 public String deleteOrderItem(){
	     orderItemService.delete(orderItem);
	     return "success.jsp";
	 }
	 
	 //��������
	 @Action("forecreateOrder")
	 public String createOrder(){
	     List<OrderItem> ois= (List<OrderItem>) ActionContext.getContext().getSession().get("orderItems");
	     if(ois.isEmpty())
	         return "login.jsp";
	   
	     User user =(User) ActionContext.getContext().getSession().get("user");
	     String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +RandomUtils.nextInt(10000);
	      
	     order.setOrderCode(orderCode);
	     order.setCreateDate(new Date());
	     order.setUser(user);
	     order.setStatus(OrderService.waitPay);
	      
	     total = orderService.createOrder(order, ois);
	     return "alipayPage";
	 }
	 
	 //ȷ��֧������
	   @Action("forealipay")
	    public String forealipay(){
	        return "alipay.jsp";
	    }
	   //֧���ɹ�ҳ��
	   @Action("forepayed")
	   public String payed() {
	       t2p(order);
	       order.setStatus(OrderService.waitDelivery);
	       order.setPayDate(new Date());
	       orderService.update(order);
	       return "payed.jsp";    
	   }
	   //��ѯ���з�ɾ���Ķ�����
	   @Action("forebought")
	   public String bought() {
	       User user =(User) ActionContext.getContext().getSession().get("user");
	       orders= orderService.listByUserWithoutDelete(user);
	       orderItemService.fill(orders);
	       return "bought.jsp";       
	   } 
	   //ȷ���ջ�
	   @Action("foreconfirmPay")
	   public String confirmPay() {
	       t2p(order);
	       orderItemService.fill(order);
	       return "confirmPay.jsp";       
	   }
	   //ȷ���ջ�ɹ�
	   @Action("foreorderConfirmed")
	   public String orderConfirmed() {
	       t2p(order);
	       order.setStatus(OrderService.waitReview);
	       order.setConfirmDate(new Date());
	       orderService.update(order);
	       return "orderConfirmed.jsp";
	   }
	   //ɾ������
	   @Action("foredeleteOrder")
	   public String deleteOrder(){
	       t2p(order);
	       order.setStatus(OrderService.delete);
	       orderService.update(order);
	       return "success.jsp";      
	   }
	   //���۲�Ʒ
	   @Action("forereview")
	   public String review() {
	       t2p(order);
	       orderItemService.fill(order);
	       product = order.getOrderItems().get(0).getProduct();
	       reviews = reviewService.listByParent(product);
	       productService.setSaleAndReviewNumber(product);
	       return "review.jsp";       
	   }
	   //�ύ����
	   @Action("foredoreview")
	   public String doreview() {
	       t2p(order);
	       t2p(product);
	        
	       order.setStatus(OrderService.finish);
	        
	       String content = review.getContent();
	       content = HtmlUtils.htmlEscape(content);    
	       User user =(User) ActionContext.getContext().getSession().get("user");     
	       review.setContent(content);
	       review.setProduct(product);
	       review.setCreateDate(new Date());
	       review.setUser(user);
	        
	       reviewService.saveReviewAndUpdateOrderStatus(review,order);
	         
	       showonly = true;
	       return "reviewPage";
	   } 
}
