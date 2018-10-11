package com.gyb.shop.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gyb.shop.pojo.User;
import com.gyb.shop.service.UserService;

@Service
public class UserServiceImpl extends  BaseServiceImpl implements UserService{

	@Override
	public boolean isExist(String name) {
		List l = list("name",name);//调用list方法，根据name进行查询；
        if(!l.isEmpty())
            return true;
        return false;
	}

	@Override
	public User get(String name, String password) {
		 List<User> l  = list("name",name, "password",password);
	        if(l.isEmpty())
	            return null;
	        return l.get(0);
	}

}
