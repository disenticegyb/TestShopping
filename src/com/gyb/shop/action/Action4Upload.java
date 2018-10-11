package com.gyb.shop.action;

import java.io.File;

/**
 * 创建Action4Upload,专门负责图片的上传；
 * @author disentice
 *
 */
public class Action4Upload {

	protected File img;//声明图片文件
    protected String imgFileName;//声明图片名字
    protected String imgContentType;//声明图片类型
     
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
