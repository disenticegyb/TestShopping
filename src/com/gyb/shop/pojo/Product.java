package com.gyb.shop.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	//����������ͺͲ�Ʒһ�Զࣻ
	@ManyToOne
	@JoinColumn(name = "cid")
	private Category category;

	private String name;
	//��Ʒ���ܡ�������
	private String subTitle;
	//ԭ��
	private float originalPrice;
	//�ۺ��
	private float promotePrice;
	//���
	private int stock;
	//��������
	private Date createDate;

	//transient˲ʱ�ģ������ֶβ���������ݿ��У������ڲ�ѯ��ʾ
	//�򵥲�ƷͼƬ
	@Transient
    private ProductImage firstProductImage;
	//��ͼƬ����
    @Transient
    private List<ProductImage> productSingleImages;
    //��Ʒ����ͼƬ����
    @Transient
    private List<ProductImage> productDetailImages;
    //��Ʒ��������
    @Transient
    private int reviewCount;
	//����
    @Transient
    private int saleCount;
    
    public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}

	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}

	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}

	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}

	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}