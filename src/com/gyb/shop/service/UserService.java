package com.gyb.shop.service;

import com.gyb.shop.pojo.User;

public interface UserService extends BaseService{
	//�����ж��û��Ƿ����
	boolean isExist(String name);
	//ͨ�������name����������ȡ����
	User get(String name, String password);
}
