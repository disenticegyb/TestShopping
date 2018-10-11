package com.gyb.shop.service;

import com.gyb.shop.pojo.User;

public interface UserService extends BaseService{
	//增加判断用户是否存在
	boolean isExist(String name);
	//通过对象的name和密码来获取对象
	User get(String name, String password);
}
