package com.gyb.shop.action;

import java.io.File;

/**
 * ����Action4Upload,ר�Ÿ���ͼƬ���ϴ���
 * @author disentice
 *
 */
public class Action4Upload {

	protected File img;//����ͼƬ�ļ�
    protected String imgFileName;//����ͼƬ����
    protected String imgContentType;//����ͼƬ����
     
   public File getImg() {
       return img;
   }
   public void setImg(File img) {
       this.img = img;
   }
   public String getImgFileName() {
       return imgFileName;
   }
   public void setImgFileName(String imgFileName) {
       this.imgFileName = imgFileName;
   }
   public String getImgContentType() {
       return imgContentType;
   }
   public void setImgContentType(String imgContentType) {
       this.imgContentType = imgContentType;
   }
}
